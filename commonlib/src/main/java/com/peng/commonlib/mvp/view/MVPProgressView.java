package com.peng.commonlib.mvp.view;

/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 *      MVP 分层架构: 进度条 View 层，带有生命周期、进度条
 */
public interface MVPProgressView extends MVPLifecycleView {
    /**
     * 显示加载中
     */
    void showProgress();

    /**
     * 隐藏加载中
     */
    void hideProgress();
}
