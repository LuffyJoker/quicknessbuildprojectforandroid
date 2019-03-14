package com.peng.commonlib.rx.observer.single;

import com.peng.commonlib.ui.base.view.MVPProgressView;

import io.reactivex.disposables.Disposables;
import io.reactivex.observers.ResourceSingleObserver;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 */
public abstract class ProgressSingleObserver<T> extends ResourceSingleObserver<T> {

    private MVPProgressView mMvpProgressView;
    private boolean mShowProgress;
    private boolean mHideProgress;

    public ProgressSingleObserver(MVPProgressView mvpProgressView, boolean showProgress, boolean hideProgress) {
        mMvpProgressView = mvpProgressView;
        mShowProgress = showProgress;
        mHideProgress = hideProgress;
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