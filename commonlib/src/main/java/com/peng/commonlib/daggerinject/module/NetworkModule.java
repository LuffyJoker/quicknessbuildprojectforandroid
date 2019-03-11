package com.peng.commonlib.daggerinject.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.peng.commonlib.BuildConfig;
import com.peng.commonlib.constant.NamedConstant;
import com.peng.commonlib.interceptor.MockInterceptor;
import com.peng.commonlib.network.ApiHelper;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * create by Mr.Q on 2019/2/18.
 * 类介绍：
 * 网络层依赖提供者，被 AppComponent 引用
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                //注意:此构建方式，必须显示的在你的对象中使用[@Expose]注解来暴露你的字段，否则不会被序列化
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
//                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
    }

    @Singleton
    @Provides
    @Named(NamedConstant.MOCK_INTERCEPTOR)
    Interceptor provideMockInterceptor() {
        return new MockInterceptor();
    }

//    @Singleton
//    @Provides
//    @Named(NamedConstant.HEADER_INTERCEPTOR)
//    Interceptor provideHeaderInterceptor(EnvironmentRepo environmentRepo, JSON json){
//        return new HeaderInterceptor(environmentRepo, json);
//    }
//
//    @Singleton
//    @Provides
//    @Named(NamedConstant.QUERY_PARAM_INTERCEPTOR)
//    Interceptor provideQueryParamInterceptor(){
//        return new QueryParamInterceptor();
//    }
//
//    @Singleton
//    @Provides
//    @Named(NamedConstant.HTTP_LOGGING_INTERCEPTOR)
//    Interceptor provideHttpLoggingInterceptor() {
//        val logger = new LoggingInterceptor.Logger {
//            override fun log(message: String) {
//                Logger.t("OkHttp").d(message)
//            }
//        }
//        LoggingInterceptor loggingInterceptor = new LoggingInterceptor(logger);
//        return LoggingInterceptor(logger).apply { level = LoggingInterceptor.Level.BODY }
//    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(

            @Named(NamedConstant.MOCK_INTERCEPTOR) Interceptor mockInterceptor
//            @Named(NamedConstant.HEADER_INTERCEPTOR) Interceptor headerInterceptor,
//            @Named(NamedConstant.QUERY_PARAM_INTERCEPTOR) Interceptor queryParamInterceptor,
//            @Named(NamedConstant.HTTP_LOGGING_INTERCEPTOR) Interceptor httpLoggingInterceptor
    ) {

        //开启Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            // TODO 加密 Interceptor 测试可用后才能启用
//            builder.addInterceptor(EncryptionInterceptor())
            builder.addInterceptor(mockInterceptor);
//            builder.addInterceptor(headerInterceptor);
//            builder.addInterceptor(queryParamInterceptor);
            builder.addInterceptor(logInterceptor);
//            builder.networkInterceptors().add(new StethoInterceptor());//可利用chrome对HTTP进行拦截显示
            // TODO 网络Interceptor实现之后再启用
//            builder.addNetworkInterceptor(NetworkInterceptor())
        }
        // 信任所有 Https 证书。因为服务端是 CA 证书，肯定安全，所以直接信任就行了。
        builder.sslSocketFactory(sslSocketFactory(), trustManager);
        builder.hostnameVerifier(hostnameVerifier);

        return builder.build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(
            OkHttpClient okHttpClient,
            CallAdapter.Factory callAdapterFactory) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .client(okHttpClient)
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Singleton
    @Provides
    CallAdapter.Factory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public ApiHelper provideApiHelper(Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }


    private HostnameVerifier hostnameVerifier = new HostnameVerifier() {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * SSLSocket、拓展 Socket,提供使用 SS L或 TLS 协议的安全套接字，是基于Socket 的，但是添加了安全保护层
     * SSLSocketFactory、抽象类，扩展自 SocketFactory，是 SSLSocket 的工厂
     *
     * @author pq
     * create at 2019/2/24
     */
    private SSLSocketFactory sslSocketFactory() {
        // 接口，信任管理器
        TrustManager[] trustAllCerts = new TrustManager[1];
        trustAllCerts[0] = trustManager;
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * TrustManger 的子接口，管理 X509 证书，验证远程安全套接字
     *
     * @author pq
     * create at 2019/2/24
     */
    private X509TrustManager trustManager = new X509TrustManager() {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };

}
