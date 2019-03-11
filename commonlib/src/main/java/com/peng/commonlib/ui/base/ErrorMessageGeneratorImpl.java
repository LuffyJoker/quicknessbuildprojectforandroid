package com.peng.commonlib.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.peng.commonlib.BuildConfig;
import com.peng.commonlib.ui.base.exception.HttpExceptionUnifyHand;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 * 错误消息生成器默认实现
 * 有 context 时从 string.xml 中取 ErrorMessage（国际化考虑），无 context 时使用默认中文 ErrorMessage
 */
public class ErrorMessageGeneratorImpl<T> implements OnErrorMessageGenerator {

    private T t;

    public ErrorMessageGeneratorImpl(T t) {
        this.t = t;
    }


    @Override
    public CharSequence generateErrorMessage(Throwable throwable) {
        Context context = null;
        if (t instanceof Context) {
            context = (Context) t;
        } else if (t instanceof Fragment) {
            context = ((Fragment) t).getContext();
        }

        if (context == null) {
            return HttpExceptionUnifyHand.parsedMessage(throwable, BuildConfig.DEBUG);
        } else {
            return HttpExceptionUnifyHand.parsedMessage(context, throwable, BuildConfig.DEBUG);
        }
    }
}

