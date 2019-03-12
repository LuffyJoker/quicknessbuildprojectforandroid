package com.peng.commonlib.network.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 *      请求参数拦截器，可以在此处添加额外的请求参数，类似注释掉的代码
 */
public class QueryParamInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request;
        HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                // TODO 使用正确 QueryParameter
//            .addQueryParameter("uid", "d2807c895f0348a180148c9dfa6f2feeac0781b5")
//            .addQueryParameter("deviceModel", "d2807c895f0348a180148c9dfa6f2feeac0781b5")
                .build();
        request = originalRequest.newBuilder().url(modifiedUrl).build();
        return chain.proceed(request);
    }
}
