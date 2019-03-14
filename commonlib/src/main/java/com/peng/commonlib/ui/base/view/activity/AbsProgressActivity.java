package com.peng.commonlib.ui.base.view.activity;

import com.blankj.utilcode.util.LogUtils;
import com.peng.commonlib.dialog.ProgressDialogFragment;
import com.peng.commonlib.ui.base.view.MVPProgressView;
import com.peng.commonlib.ui.base.view.dialog.AbsBaseDialogFragment;
import com.peng.dglib.other.DialogFragmentOptions;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 * 1、带进度条抽象类，继承自 Dagger 抽象类，扩展以支持耗时任务中的进度条显隐
 */
public abstract class AbsProgressActivity extends AbsMVPActivity implements MVPProgressView {


    private AbsBaseDialogFragment absBaseDialogFragment;

    @Override
    public void showProgress() {
        ProgressDialogFragment absBaseDialog = new ProgressDialogFragment();
        absBaseDialogFragment = absBaseDialog;
        absBaseDialog.showOnWindow(getSupportFragmentManager(), ProgressDialogFragment.class.getName());
    }

    @Override
    public void hideProgress() {
        if (absBaseDialogFragment != null) {
            absBaseDialogFragment.dismiss();
        }
        absBaseDialogFragment = null;
    }

}
