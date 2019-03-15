package com.peng.commonlib.ui.base.exception;

import android.content.Context;
import android.os.NetworkOnMainThreadException;

import com.google.gson.JsonParseException;
import com.peng.commonlib.R;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.CancellationException;

import retrofit2.HttpException;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 *      Http 异常统一处理
 */
public class HttpExceptionUnifyHand {

    public static CharSequence parsedMessage(Context context, Throwable throwable, boolean debug) {

        String msg = null;

        if (throwable instanceof HttpException || throwable instanceof UnknownHostException) {
            msg = context.getString(R.string.message_network_error);
        } else if (throwable instanceof SocketException || throwable instanceof ConnectException) {
            msg = context.getString(R.string.message_connect_failure);
        } else if (throwable instanceof SocketTimeoutException) {
            msg = context.getString(R.string.message_connect_timeout);
        } else if (throwable instanceof IOException) {
            msg = context.getString(R.string.message_io_exception);
        } else if (throwable instanceof JsonParseException) {
            msg = context.getString(R.string.message_parse_error);
        } else if (throwable instanceof NullPointerException) {
            msg = context.getString(R.string.message_null_pointer);
        } else if (throwable instanceof NetworkOnMainThreadException) {
            msg = context.getString(R.string.message_network_on_main_thread);
        } else if (throwable instanceof CancellationException) {
            msg = context.getString(R.string.message_cancellation);
        } else {
            if (throwable.getMessage() != null) {
                msg = throwable.getMessage();
            }
        }
        if (debug) {
            if (throwable.getMessage() != null) {
                return throwable.getMessage();
            } else {
                return msg;
            }
        } else {
            return msg;
        }
    }

    public static CharSequence parsedMessage(Throwable throwable, boolean debug) {

        String msg = null;

        if (throwable instanceof HttpException || throwable instanceof UnknownHostException) {
            msg = "网络错误";
        } else if (throwable instanceof SocketException || throwable instanceof ConnectException) {
            msg = "连接失败";
        } else if (throwable instanceof SocketTimeoutException) {
            msg = "连接超时";
        } else if (throwable instanceof IOException) {
            msg = "IO异常";
        } else if (throwable instanceof JsonParseException) {
            msg = "解析失败";
        } else if (throwable instanceof NullPointerException) {
            msg = "空指针异常";
        } else if (throwable instanceof NetworkOnMainThreadException) {
            msg = "主线程耗时操作";
        } else if (throwable instanceof CancellationException) {
            msg = "已取消请求";
        } else {
            if (throwable.getMessage() != null) {
                msg = throwable.getMessage();
            }
        }

        if (debug) {
            if (throwable.getMessage() != null) {
                return throwable.getMessage();
            } else {
                return msg;
            }
        } else {
            return msg;
        }
    }
}
