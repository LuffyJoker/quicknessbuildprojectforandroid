package com.peng.commonlib.base.activity;

import com.peng.commonlib.base.view.MVPLifecycleView;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 */
public abstract class AbsMVPActivity extends AbsDaggerActivity implements MVPLifecycleView {

    @Override
    protected void injection() {
        attachPresenter();
        super.injection();
    }

    @Override
    protected void onDestroy() {
        detachPresenter();
        super.onDestroy();
    }

    /**
     * 连接Presenter
     */
    abstract void attachPresenter();

    /**
     * 分离Presenter
     */
    abstract void detachPresenter();
}
