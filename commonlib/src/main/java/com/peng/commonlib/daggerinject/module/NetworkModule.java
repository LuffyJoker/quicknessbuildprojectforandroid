package com.peng.commonlib.daggerinject.module;

import com.blankj.utilcode.util.LogUtils;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.peng.commonlib.BaseApplication;
import com.peng.commonlib.BuildConfig;
import com.peng.commonlib.data.constant.NamedConstant;
import com.peng.commonlib.data.network.ApiHelper;
import com.peng.commonlib.data.network.interceptor.HeaderInterceptor;
import com.peng.commonlib.data.network.interceptor.LoggingInterceptor;
import com.peng.commonlib.data.network.interceptor.MockInterceptor;

import java.io.File;
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
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
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
    @Named(NamedConstant.MOCK_INTERCEPTOR)
    Interceptor provideMockInterceptor() {
        return new MockInterceptor();
    }

    @Singleton
    @Provides
    @Named(NamedConstant.HEADER_INTERCEPTOR)
    Interceptor provideHeaderInterceptor() {
        return new HeaderInterceptor();
    }

    //
//    @Singleton
//    @Provides
//    @Named(NamedConstant.QUERY_PARAM_INTERCEPTOR)
//    Interceptor provideQueryParamInterceptor(){
//        return new QueryParamInterceptor();
//    }
//
    @Singleton
    @Provides
    @Named(NamedConstant.HTTP_LOGGING_INTERCEPTOR)
    Interceptor provideHttpLoggingInterceptor() {

        // 重写 日志拦截器日志打印方式
        LoggingInterceptor.Logger logger = new LoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                // 这一行，可以调用自己喜欢的日志打印工具进行打印
                LogUtils.d(message);
            }
        };

        // 开启 Log
        LoggingInterceptor logInterceptor = new LoggingInterceptor(logger);
        logInterceptor.setLevel(LoggingInterceptor.Level.BODY);
        return logInterceptor;
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(

            @Named(NamedConstant.MOCK_INTERCEPTOR) Interceptor mockInterceptor,
            @Named(NamedConstant.HEADER_INTERCEPTOR) Interceptor headerInterceptor,
//            @Named(NamedConstant.QUERY_PARAM_INTERCEPTOR) Interceptor queryParamInterceptor,
            @Named(NamedConstant.HTTP_LOGGING_INTERCEPTOR) Interceptor httpLoggingInterceptor,
            Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            //builder.addInterceptor(EncryptionInterceptor()) 加密 Interceptor 测试可用后才能启用
            builder.addInterceptor(headerInterceptor);
            builder.addInterceptor(httpLoggingInterceptor);
            builder.addInterceptor(mockInterceptor);
//            builder.addInterceptor(queryParamInterceptor);
            builder.networkInterceptors().add(new StethoInterceptor());//可利用 chrome 对 HTTP 进行拦截显示，有时需要翻墙才能操作，很消耗性能，只在debug模式下才开启该功能
            //builder.addNetworkInterceptor(NetworkInterceptor()) 网络Interceptor实现之后再启用
        }
        // 信任所有 Https 证书。因为服务端是 CA 证书，肯定安全，所以直接信任就行了。
        builder.sslSocketFactory(sslSocketFactory(), trustManager);
        builder.hostnameVerifier(hostnameVerifier);
        builder.cache(cache);// 加入okHttp缓存功能
        return builder.build();
    }


    @Singleton
    @Provides
    public Retrofit provideRetrofit(
            OkHttpClient okHttpClient,
            CallAdapter.Factory callAdapterFactory,
            GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .client(okHttpClient)
                .addCallAdapterFactory(callAdapterFactory) // 将 call 包装成 Observable
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        // 为了避免使用 Gson 时遇到 locale 时间格式影响 Date 格式的问题，使用 GsonBuilder 来创建 Gson 对象，
        // 在创建过程中调用 GsonBuilder.setDateFormat(String) 指定一个固定的格式即可。
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    Cache provideCache() {
        //缓存
        File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        return cache;
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
