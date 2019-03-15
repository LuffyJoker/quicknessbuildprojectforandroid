package com.peng.commonlib.rx.observer.single;

import com.peng.commonlib.ui.base.view.MVPProgressView;

import io.reactivex.disposables.Disposables;
import io.reactivex.observers.ResourceSingleObserver;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 *      控制加载进度条自动显示与隐藏
 */
public abstract class ProgressSingleObserver<T> extends ResourceSingleObserver<T> {

    private MVPProgressView mMvpProgressView;
    private boolean mShowProgress = true;
    private boolean mHideProgress = true;

    public ProgressSingleObserver(MVPProgressView mvpProgressView) {
        mMvpProgressView = mvpProgressView;
    }

    @Override
    protected void onStart() {
        super.onStart();
        add(Disposables.fromRunnable(new Runnable() {
            @Override
            public void run() {
                mMvpProgressView.hideProgress();
            }
        }));

        if (mShowProgress) {
            mMvpProgressView.showProgress();
        }
    }

    @Override
    public void onSuccess(T t) {
        if (mHideProgress) {
            mMvpProgressView.hideProgress();
        }
    }

    @Override
    public void onError(Throwable e) {
        mMvpProgressView.hideProgress();
    }
}