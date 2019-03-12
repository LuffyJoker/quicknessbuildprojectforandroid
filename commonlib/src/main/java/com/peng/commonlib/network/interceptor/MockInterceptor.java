package com.peng.commonlib.network.interceptor;

import com.peng.commonlib.network.HttpDebugUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mr.Q on 2019/2/24.
 * 描述：
 *      Mock 数据的拦截器，用于 Http 的调试模式，从本地的 assets 文件获取 Json 数据
 *      1
 */
public class MockInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response;
        try {
            //检查是否开启调试模式
            if (HttpDebugUtils.isDebug()) {
                //不管是否开了调试模式，那么如果未找到本地数据依然会走网络请求
                response = HttpDebugUtils.createResponse(request);
            } else {
                //如果没有开启调试模式，那么检查这个请求是否为一个调试请求
                //如果是，那么走本地，不是走正常的网络请求逻辑
                response = HttpDebugUtils.checkSingleRequestDebug(request);
            }

            if (response == null) {
                return chain.proceed(request);
            } else {
                return response;
            }

        } catch (Exception e) {
            throw e;
        }
    }
}
