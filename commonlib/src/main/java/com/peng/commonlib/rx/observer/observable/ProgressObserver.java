package com.peng.commonlib.rx.observer.observable;

import com.peng.commonlib.ui.base.view.MVPProgressView;

import io.reactivex.disposables.Disposables;
import io.reactivex.observers.ResourceObserver;

/**
 * Created by Mr.Q on 2019/3/14
 * 描述：
 * 支持进度条显隐的 FlowableObserver
 * 注意：不要与 Flowable.xxxToMainWithLifecycleProgress() 一块儿使用，使用一种即可
 */
public abstract class ProgressObserver<T> extends ResourceObserver<T> {

    private MVPProgressView mvpProgressView;
    private boolean showProgress = true;
    private boolean hideProgress = true;

    public ProgressObserver(MVPProgressView mvpProgressView, boolean showProgress, boolean hideProgress) {
        this.mvpProgressView = mvpProgressView;
        this.showProgress = showProgress;
        this.hideProgress = hideProgress;
    }

    public ProgressObserver(MVPProgressView mvpProgressView) {
        this.mvpProgressView = mvpProgressView;
    }

    @Override
    protected void onStart() {
        super.onStart();
        add(Disposables.fromRunnable(new Runnable() {
            @Override
            public void run() {
                if(mvpProgressView != null){
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
}
