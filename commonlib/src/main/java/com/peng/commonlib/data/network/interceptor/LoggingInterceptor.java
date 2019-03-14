package com.peng.commonlib.data.network.interceptor;

import com.peng.commonlib.data.network.interceptor.formatter.OnFormatter;
import com.peng.commonlib.data.network.interceptor.formatter.StringFormatter;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

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

import static okhttp3.internal.platform.Platform.INFO;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：自定义日志拦截器
 * 1、源码来自 https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/src/main/java/okhttp3/logging/HttpLoggingInterceptor.java
 * 2、源码中每一行均使用logger.log()打印信息，会造成打印一行消息带上格式信息，不方便查看日志
 * 3、因此采用字符串拼接的方式，只在 request 和 response 这两处各自拼接完成后，使用 logger.log() 打印
 * 4、Json 字符串格式化策略，Formatter 参照如下项目：
 * https://github.com/parkingwang/okhttp3-loginterceptor
 */
public class LoggingInterceptor implements Interceptor {

    private OnFormatter formatter = new StringFormatter();
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String SEPARATOR = "\n";

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

    public interface Logger {
        void log(String message);

        /**
         * A {@link Logger} defaults output appropriate for the current platform.
         */
        Logger DEFAULT = new Logger() {
            @Override
            public void log(String message) {
                Platform.get().log(INFO, message, null);
            }
        };
    }

    public LoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public LoggingInterceptor(Logger logger) {
        this.logger = logger;
    }

    private final Logger logger;

    private volatile Level level = Level.NONE;

    /**
     * Change the level at which this interceptor logs.
     */
    public LoggingInterceptor setLevel(Level level) {
        if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead.");
        this.level = level;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Level level = this.level;

        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }

        boolean logBody = level == Level.BODY;
        boolean logHeaders = logBody || level == Level.HEADERS;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        String requestStartMessage = "--> "
                + request.method()
                + ' ' + request.url()
                + (connection != null ? " " + connection.protocol() : "") + SEPARATOR;
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)" + SEPARATOR;
        }


        if (logHeaders) {
            if (hasRequestBody) {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                if (requestBody.contentType() != null) {
                    requestStartMessage += "Content-Type:" + requestBody.contentType() + SEPARATOR;
                }
                if (requestBody.contentLength() != -1) {
                    requestStartMessage += "Content-Length: " + requestBody.contentLength() + SEPARATOR;
                }
            }

            Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    requestStartMessage += name + ": " + headers.value(i) + SEPARATOR;
                }
            }
            requestStartMessage += SEPARATOR;
            if (!logBody || !hasRequestBody) {
                requestStartMessage += "--> END " + request.method() + SEPARATOR;
            } else if (bodyHasUnknownEncoding(request.headers())) {
                requestStartMessage += "--> END " + request.method() + " (encoded body omitted)" + SEPARATOR;
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                if (isPlaintext(buffer)) {
                    requestStartMessage += formatter.format(buffer.readString(charset)) + SEPARATOR;
                    requestStartMessage += SEPARATOR;
                    requestStartMessage += "--> END " + request.method() + " (" + requestBody.contentLength() + "-byte body)" + SEPARATOR;
                } else {
                    requestStartMessage += "--> END " + request.method() + " (binary " + requestBody.contentLength() + "-byte body omitted)" + SEPARATOR;
                }
            }
        }

        logger.log(requestStartMessage);

        String responseMessage = "";
        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            responseMessage += "<-- HTTP FAILED:" + e + SEPARATOR;
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        responseMessage += "<-- "
                + response.code()
                + (response.message().isEmpty() ? "" : ' ' + response.message())
                + ' ' + response.request().url()
                + " (" + tookMs + "ms" + (!logHeaders ? ", " + bodySize + " body" : "") + ')' + SEPARATOR;

        if (logHeaders) {
            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                responseMessage += headers.name(i) + ": " + headers.value(i) + SEPARATOR;
            }

            if (!logBody || !HttpHeaders.hasBody(response)) {
                responseMessage += "<-- END HTTP" + SEPARATOR;
            } else if (bodyHasUnknownEncoding(response.headers())) {
                responseMessage += "<-- END HTTP (encoded body omitted)" + SEPARATOR;
            } else {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Long gzippedLength = null;
                if ("gzip".equalsIgnoreCase(headers.get("Content-Encoding"))) {
                    gzippedLength = buffer.size();
                    GzipSource gzippedResponseBody = null;
                    try {
                        gzippedResponseBody = new GzipSource(buffer.clone());
                        buffer = new Buffer();
                        buffer.writeAll(gzippedResponseBody);
                    } finally {
                        if (gzippedResponseBody != null) {
                            gzippedResponseBody.close();
                        }
                    }
                }
                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                if (!isPlaintext(buffer)) {

                    responseMessage += SEPARATOR;
                    responseMessage += "<-- END HTTP (binary " + buffer.size() + "-byte body omitted)" + SEPARATOR;
                    return response;
                }

                if (contentLength != 0) {
                    responseMessage += SEPARATOR;
                    responseMessage += formatter.format(buffer.clone().readString(charset)) + SEPARATOR;
                }
                responseMessage += SEPARATOR;
                if (gzippedLength != null) {
                    responseMessage += "<-- END HTTP (" + buffer.size() + "-byte, " + gzippedLength + "-gzipped-byte body)" + SEPARATOR;
                } else {
                    responseMessage += "<-- END HTTP (" + buffer.size() + "-byte body)" + SEPARATOR;
                }
            }
        }
        logger.log(responseMessage);
        return response;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
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
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyHasUnknownEncoding(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null
                && !contentEncoding.equalsIgnoreCase("identity")
                && !contentEncoding.equalsIgnoreCase("gzip");
    }
}
