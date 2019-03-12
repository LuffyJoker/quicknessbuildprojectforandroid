package com.peng.commonlib.network.interceptor;

import com.peng.commonlib.network.interceptor.formatter.OnFormatter;
import com.peng.commonlib.network.interceptor.formatter.StringFormatter;

import java.io.EOFException;
import java.io.IOException;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.alibaba.android.arouter.utils.Consts.SEPARATOR;
import static okhttp3.internal.platform.Platform.INFO;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 */
public class LoggingInterceptor implements Interceptor {

    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static final String SEPARATOR = "\n";

    private boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount;
            if (buffer.size() < 64){
              byteCount = buffer.size();
            }  else {
                byteCount = 64;
            }
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i <= 15; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false;
        }
    }


    public LoggingInterceptor.Level level = LoggingInterceptor.Level.BODY;

    private LoggingInterceptor.Logger logger = LoggingInterceptor.Logger.DEFAULT;

    private Set headersToRedact = new TreeSet<String>();

    private OnFormatter formatter = new StringFormatter();

    /**
     * 指定敏感的 Header，打印黑块儿而不是真实内容
     *
     * eg:
     * logging.redactHeader("Authorization")
     * logging.redactHeader("Cookie")
     */
    void redactHeader(String name) {
        headersToRedact = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        headersToRedact.add(name);
    }

    public LoggingInterceptor() {

    }

    public LoggingInterceptor(LoggingInterceptor.Logger logger) {
        this.logger = logger;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        LoggingInterceptor.Level level = this.level;

        Request request = chain.request();
        if (level == LoggingInterceptor.Level.NONE) {
            return chain.proceed(request);
        }

        boolean logBody = level == LoggingInterceptor.Level.BODY;
        boolean logHeaders = logBody || level == LoggingInterceptor.Level.HEADERS;

        RequestBody requestBody = request.body();

        Connection connection = chain.connection();
        String requestMessage = null;
        if(connection != null){
            requestMessage = ("--> " + request.method() + ' ' + request.url() + " " + connection.protocol()) + SEPARATOR;
        }else{
            requestMessage = ("--> " + request.method() + ' ' + request.url() + "") + SEPARATOR;
        }
        if (!logHeaders && requestBody != null) {
            requestMessage += " (" + requestBody.contentLength() + "-byte body)" + SEPARATOR;
        }

        if (logHeaders) {
            if (requestBody != null) {
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    requestMessage += "Content-Type: $contentType$SEPARATOR";
                }

                long contentLength = requestBody.contentLength();
                if (contentLength != -1L) {
                    requestMessage += "Content-Length: $contentLength$SEPARATOR";
                }
            }

            Headers headers = request.headers();
            int i = 0;
            int count = headers.size();
            while (i < count) {
                String name = headers.name(i);
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    requestMessage += headerMessage(headers, i);
                }
                i++;
            }

            requestMessage += SEPARATOR;
            if (!logBody || requestBody == null) {
                requestMessage += "--> END " + request.method() + SEPARATOR;
            } else if (bodyHasUnknownEncoding(request.headers())) {
                requestMessage += "--> END " + request.method() + " (encoded body omitted)" + SEPARATOR;
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset;
                if(requestBody.contentType() != null){
                    charset = requestBody.contentType().charset(UTF8);
                }else{
                    charset = UTF8;
                }

                if (isPlaintext(buffer)) {
                    String requestBodyString = buffer.readString(charset);
                    requestMessage += formatter.format(requestBodyString) + SEPARATOR;
                    requestMessage += SEPARATOR;
                    requestMessage += "--> END " + request.method() + " (" + requestBody.contentLength() + "-byte body)" + SEPARATOR;
                } else {
                    requestMessage += "--> END " + request.method() + " (binary " + requestBody.contentLength() + "-byte body omitted)" + SEPARATOR;
                }
            }
        }
        logger.log(requestMessage);

        String responseMessage = "";
        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            responseMessage += "<-- HTTP FAILED: $e$SEPARATOR";
            throw e;
        }

        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody;
        if(response.body() == null){
            throw new NullPointerException("请求体为null ");
        }else{
            responseBody = response.body();
        }
        long contentLength = responseBody.contentLength();
        String bodySize;

        if (contentLength != -1L){
            bodySize = contentLength + "-byte";
        }  else{
            bodySize = "unknown-length";
        }
        if (response.message().isEmpty()){
            if (!logHeaders){
                responseMessage = "<-- " + response.code() +2 + "" +' '+ response.request().url() + " (" + tookMs + "ms" +", $bodySize body"+ ')' + SEPARATOR;
            }else{
                responseMessage = "<-- " + response.code() +2 + "" +' '+ response.request().url() + " (" + tookMs + "ms" +""+ ')' + SEPARATOR;
            }
        }else{
            if (!logHeaders){
                responseMessage = "<-- " + response.code() +2 + ' ' + response.message()+' '+ response.request().url() + " (" + tookMs + "ms" +", $bodySize body"+ ')' + SEPARATOR;
            }else{
                responseMessage = "<-- " + response.code() +2 + ' ' + response.message()+' '+ response.request().url() + " (" + tookMs + "ms" +""+ ')' + SEPARATOR;
            }
        }

        if (logHeaders) {
            Headers headers = response.headers();
            int i = 0;
            int count = headers.size();
            while (i < count) {
                responseMessage += headerMessage(headers, i++);
            }

            if (!logBody || !HttpHeaders.hasBody(response)) {
                responseMessage += "<-- END HTTP$SEPARATOR";
            } else if (bodyHasUnknownEncoding(response.headers())) {
                responseMessage += "<-- END HTTP (encoded body omitted)$SEPARATOR";
            } else {
                BufferedSource source = responseBody.source();
                source.request(java.lang.Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                long gzippedLength = 0;
                if ("gzip".equalsIgnoreCase(headers.get("Content-Encoding"))) {
                    gzippedLength = buffer.size();
                    GzipSource gzippedResponseBody = null;
                    try {
                        gzippedResponseBody = new GzipSource(buffer.clone());
                        buffer = new Buffer();
                        buffer.writeAll(gzippedResponseBody);
                    } finally {
                        gzippedResponseBody.close();
                    }
                }
                Charset charset;
                if(responseBody.contentType() != null){
                    charset = requestBody.contentType().charset(UTF8);
                }else{
                    charset = UTF8;
                }


                if (!isPlaintext(buffer)) {
                    responseMessage += SEPARATOR;
                    responseMessage += "<-- END HTTP (binary " + buffer.size() + "-byte body omitted)" + SEPARATOR;
                    return response;
                }

                if (contentLength != 0L) {
                    responseMessage += SEPARATOR;
                    String responseBodyString = buffer.clone().readString(charset);
                    responseMessage += formatter.format(responseBodyString) + SEPARATOR;
                }

                responseMessage += SEPARATOR;
                if (gzippedLength > 0) {
                    responseMessage += "<-- END HTTP (" + buffer.size() + "-byte, " + gzippedLength + "-gzipped-byte body)" + SEPARATOR;
                }else{
                    responseMessage += "<-- END HTTP (" + buffer.size() + "-byte body)" + SEPARATOR;
                }
            }
        }
        logger.log(responseMessage);

        return response;
    }

    public interface Logger {

        Logger DEFAULT = new Logger() {
            @Override
            public void log(String message) {
                Platform.get().log(INFO, message, null);
            }
        };

        void log(String message);
    }

    private boolean bodyHasUnknownEncoding(Headers headers){
        String contentEncoding = headers.get("Content-Encoding");
        return (contentEncoding != null
                && !contentEncoding.equalsIgnoreCase("identity")
                && !contentEncoding.equalsIgnoreCase("gzip"));
    }

    private String headerMessage(Headers headers, int i) {
        String name = headers.name(i);
        String value;
        if (headersToRedact.contains(name)){
            value = "██";
        }else{
            value = headers.value(i);
        }
        return name+":"+ value +SEPARATOR;
    }

    public enum Level {

        /**
         * No logs.
         */
        NONE,

        /**
         * Logs request and response lines.
         *
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1 (3-byte body)
         *
         * <-- 200 OK (22ms, 6-byte body)
         * }</pre>
         */
        BASIC,

        /**
         * Logs request and response lines and their respective headers.
         *
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * }</pre>
         */
        HEADERS,

        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         *
         * <p>Example:
         * <pre>{@code
         * --> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
         * }</pre>
         */
        BODY
    }

}
