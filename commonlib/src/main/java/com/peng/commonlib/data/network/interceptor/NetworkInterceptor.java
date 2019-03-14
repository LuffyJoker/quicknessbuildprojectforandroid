package com.peng.commonlib.data.network.interceptor;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.Utils;
import com.peng.commonlib.R;

import java.io.IOException;
import java.net.ConnectException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 *      网络拦截器
 */
public class NetworkInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtils.isConnected()) {
            throw new ConnectException(Utils.getApp().getString(R.string.can_not_connect_net));
        }
        return chain.proceed(chain.request());
    }
}
