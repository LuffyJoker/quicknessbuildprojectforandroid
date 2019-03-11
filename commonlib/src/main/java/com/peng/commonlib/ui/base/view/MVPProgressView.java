package com.peng.commonlib.ui.base.view;

import com.peng.commonlib.ui.base.OnDistributeAction;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 */
public interface MVPProgressView extends MVPLifecycleView , OnDistributeAction {
    /**
     * 显示加载中
     */
    void showProgress();

    /**
     * 隐藏加载中
     */
    void hideProgress();
}
