package com.peng.commonlib.rx;

import com.blankj.utilcode.util.NetworkUtils;
import com.peng.commonlib.BaseApplication;
import com.peng.commonlib.R;

import io.reactivex.Observable;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 */
public class ApiException extends RuntimeException {

    private String mErrorCode;
    private String errorMessage;

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.mErrorCode = String.valueOf(errorCode);
        this.errorMessage = errorMessage;
    }

    public ApiException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.mErrorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApiException(String errorMessage) {
        this(0, errorMessage);
    }

    public String getErrorCode() {
        return mErrorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public static Observable<String> handleException(Throwable e) {
        String errorMsg = "";
        if (!NetworkUtils.isConnected()) {
            errorMsg = BaseApplication.getAppResources().getString(R.string.no_net);
        } else if (e instanceof ApiException) {
            errorMsg = ((ApiException) e).getErrorMessage();
        } else {
            errorMsg = BaseApplication.getAppResources().getString(R.string.net_error);
        }
        return Observable.just(errorMsg);

    }

    public static void handleException(Throwable e, OnExceptionCallBack callBack) {
        String errorMsg = "";
        if (!NetworkUtils.isConnected()) {
            errorMsg = BaseApplication.getAppResources().getString(R.string.no_net);
        } else if (e instanceof ApiException) {
            errorMsg = ((ApiException) e).getErrorMessage();
            if (callBack != null) {
                callBack.onError(errorMsg);
            }
        } else {
            errorMsg = BaseApplication.getAppResources().getString(R.string.net_error);
        }
    }


    public interface OnExceptionCallBack {
        void onError(String msg);
    }


}

