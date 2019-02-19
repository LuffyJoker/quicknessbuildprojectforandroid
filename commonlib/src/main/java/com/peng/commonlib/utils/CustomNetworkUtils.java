package com.peng.commonlib.utils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ShellUtils;

import retrofit2.HttpException;

/**
 * Created by Mr.Q on 2019/2/19.
 * 描述：
 * 自定义网络工具类
 */
public class CustomNetworkUtils {

    private static final String TAG = "CustomNetworkUtils";

    /**
     * 判断该状态码是不是 http 的状态码
     *
     * @author pq
     * create at 2019/2/19
     */
    public boolean isHttpStatusCode(Throwable throwable, int statusCode) {
        return (throwable instanceof HttpException) && (statusCode == ((HttpException) throwable).code());
    }

    /**
     * 测试 IP 是否可以 ping 通
     *
     * @author pq
     * create at 2019/2/19
     */
    public boolean testPing(String ip) {
        ShellUtils.CommandResult result = ShellUtils.execCmd(String.format("ping -c 1 %s", ip), false);
        if (result.errorMsg != null) {
            LogUtils.e("CustomNetworkUtils", "testPing() errorMsg is:" + result.errorMsg);
        }
        if (result.successMsg != null) {
            LogUtils.e("CustomNetworkUtils", "simplePing() successMsg is" + result.successMsg);
        }

        return result.result == 0;
    }

}
