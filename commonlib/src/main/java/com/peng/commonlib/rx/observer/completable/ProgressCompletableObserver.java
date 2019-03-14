package com.peng.commonlib.rx.observer.completable;

import com.peng.commonlib.ui.base.view.MVPProgressView;

import io.reactivex.disposables.Disposables;
import io.reactivex.observers.ResourceCompletableObserver;

/**
 * create by Mr.Q on 2019/3/14.
 * 类介绍：
 * 支持进度条显隐的 FlowableObserver
 * 注意：不要与 Flowable.xxxToMainWithLifecycleProgress()一块儿使用，使用一种即可
 */
public abstract class ProgressCompletableObserver extends ResourceCompletableObserver {

    private MVPProgressView mvpProgressView;
    private boolean showProgress = true;
    private boolean hideProgress = true;

    public ProgressCompletableObserver(MVPProgressView mvpProgressView) {
        this.mvpProgressView = mvpProgressView;
    }

    public ProgressCompletableObserver(MVPProgressView mvpProgressView, boolean showProgress, boolean hideProgress) {
        this.mvpProgressView = mvpProgressView;
        this.showProgress = showProgress;
        this.hideProgress = hideProgress;
    }

    @Override
    protected void onStart() {
        super.onStart();
        add(Disposables.fromRunnable(new Runnable() {
            @Override
            public void run() {
                if(mvpProgressView!=null){
                    mvpProgressView.hideProgress();
                }
            }
        }));
        if (showProgress){
            if(mvpProgressView != null){
                mvpProgressView.showProgress();
            }
        }
    }

    @Override
    public void onComplete() {
        if (hideProgress){
            if(mvpProgressView != null){
                mvpProgressView.hideProgress();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if(mvpProgressView != null){
            mvpProgressView.hideProgress();
        }
    }
}
