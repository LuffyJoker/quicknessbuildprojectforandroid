package com.peng.commonlib.ui.base.activity;

import com.peng.commonlib.dialog.ProgressDialogFragment;
import com.peng.commonlib.ui.base.dialog.AbsBaseDialogFragment;
import com.peng.commonlib.ui.base.view.MVPProgressView;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 */
public abstract class AbsProgressActivity extends AbsMVPActivity implements MVPProgressView {


    private AbsBaseDialogFragment absBaseDialogFragment;

    @Override
    public void showProgress() {
        ProgressDialogFragment absBaseDialog = new ProgressDialogFragment();
        absBaseDialogFragment = absBaseDialog;
//        showDialogFragmentOnWindow(absBaseDialog);
    }

    @Override
    public void hideProgress() {
        absBaseDialogFragment.dismiss();
        absBaseDialogFragment = null;
    }

}
