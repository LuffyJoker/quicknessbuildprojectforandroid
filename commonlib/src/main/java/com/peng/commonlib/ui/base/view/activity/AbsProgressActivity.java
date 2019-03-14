package com.peng.commonlib.ui.base.view.activity;

import com.peng.commonlib.dialog.ProgressDialogFragment;
import com.peng.commonlib.ui.base.view.MVPProgressView;
import com.peng.commonlib.ui.base.view.dialog.AbsBaseDialogFragment;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 *  1、带进度条抽象类，继承自 Dagger 抽象类，扩展以支持耗时任务中的进度条显隐
 */
public abstract class AbsProgressActivity extends AbsMVPActivity implements MVPProgressView {


    private AbsBaseDialogFragment absBaseDialogFragment;

    @Override
    public void showProgress() {
        ProgressDialogFragment absBaseDialog = new ProgressDialogFragment();
        absBaseDialogFragment = absBaseDialog;
        absBaseDialog.showOnWindow(getSupportFragmentManager(),ProgressDialogFragment.class.getName());
    }

    @Override
    public void hideProgress() {
        absBaseDialogFragment.dismiss();
        absBaseDialogFragment = null;
    }

}
