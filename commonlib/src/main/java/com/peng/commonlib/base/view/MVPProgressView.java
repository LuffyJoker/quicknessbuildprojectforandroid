package com.peng.commonlib.base.view;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
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
