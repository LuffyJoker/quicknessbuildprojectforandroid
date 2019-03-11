package com.peng.commonlib.rx;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.blankj.utilcode.util.NetworkUtils;
import com.peng.commonlib.ui.base.activity.AbsBaseActivity;
import com.peng.commonlib.ui.base.view.MVPProgressView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 */
public abstract class BaseObserver<T> implements Observer<T> {

    private AbsBaseActivity mActivity;
    private ProgressDialog progressDialog;
    private Disposable mDisposable;
    private Dialog mDialog;
    private MVPProgressView mvpProgressView;

    /**
     * 默认无效果的请求
     *
     * @param mvpProgressView
     */
    public BaseObserver(MVPProgressView mvpProgressView) {
        this.mvpProgressView = mvpProgressView;
    }

    /**
     * 默认无效果的请求
     *
     * @param mBaseActivity
     */
    public BaseObserver(AbsBaseActivity mBaseActivity) {
        this.mActivity = mBaseActivity;
    }

    /**
     * 带文字提示对话框
     *
     * @param mBaseActivity
     * @param msg           提示信息
     * @param showProgress  是否显示对话框
     * @param cancelable    是否可以取消
     */
    protected BaseObserver(AbsBaseActivity mBaseActivity, String msg, boolean showProgress, boolean cancelable) {
        this.mActivity = mBaseActivity;
        if (showProgress) {

//            mDialog = LoadingDialog.showDialogForLoading(mActivity, msg, cancelable);
            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    mDisposable.dispose();
                }
            });

        }
    }

    /**
     * 带进度条提示对话框
     *
     * @param mBaseActivity
     * @param showProgress
     */
    protected BaseObserver(AbsBaseActivity mBaseActivity, boolean showProgress) {
        this.mActivity = mBaseActivity;
        if (showProgress) {
            progressDialog = new ProgressDialog(mBaseActivity);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    mDisposable.dispose();
                }
            });
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    /**
     * 此处是事件成功接收回调
     *
     * @param value
     */
    @Override
    public void onNext(T value) {

        //这里对数据 value 的封装,这里看个人的网络回调的结果来操作
//        if (value.getTotal() > 0) {
//            T t = value.getSubject();
//            onSuccess(t);
//        } else {
//            onHandleError(value.getTitle());
//        }
        if (mDialog != null) {
            mDialog.cancel();
            mDialog = null;
        }
        onSuccess(value);

    }

    /**
     * 事件发生错误回调
     *
     * @param e
     */
    @Override
    public void onError(final Throwable e) {

        // 防止网络异常时 chain.proceed(request) 抛出异常，回调在子线程
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mDialog != null) {
                    mDialog.cancel();
                    mDialog = null;
                }
                e.printStackTrace();
                //无网络
                if (!NetworkUtils.isConnected()) {
                    onFail("无网络连接");
                } else if (e instanceof ApiException) {
                    //返回成功，服务器不正常
                    onFail(((ApiException) e).getErrorCode() + ((ApiException) e).getErrorMessage());
                } else {
                    onFail("error:" + e.getLocalizedMessage() + " Please check!");
                }
            }
        });
    }

    @Override
    public void onComplete() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    protected abstract void onSuccess(T t);

    protected abstract void onFail(String message);
}

