package com.peng.commonlib.data.network.interceptor;

import com.peng.commonlib.data.network.HttpDebugUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mr.Q on 2019/2/24.
 * 描述：
 *      Mock 数据的拦截器，用于 Http 的调试模式，从本地的 assets 文件获取 Json 数据
 *      使用方式：
 *          1、在 assets/debug_json 目录下，按照示例格式创建数据，json文件的文件名
 *          2、json文件里面的内容，就是服务器返回的json字符串
 *          3、在ApiHelper中定义接口方法时，需要使用如下注解：
 *              a、使用 mock 数据需要添加 @Headers(value = HTTP_DEBUG_HEADER)，不使用则不添加即可
 *              b、使用真实的接口数据 @GET("user/find_device_status/{deviceNo}")
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
                // 如果没有开启调试模式，那么检查这个请求是否为一个调试请求
                // 如果是，那么走本地，不是走正常的网络请求逻辑
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
