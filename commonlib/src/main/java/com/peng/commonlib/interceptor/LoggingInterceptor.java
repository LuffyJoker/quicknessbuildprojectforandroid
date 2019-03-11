package com.peng.commonlib.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;

import static okhttp3.internal.platform.Platform.INFO;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 */
public class LoggingInterceptor implements Interceptor {

    public LoggingInterceptor(LoggingInterceptor.Logger logger) {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }

    interface Logger {

        Logger DEFAULT = new Logger() {
            @Override
            public void log(String message) {
                Platform.get().log(INFO, message, null);
            }
        };

        void log(String message);
    }

}
