package com.peng.commonlib.ui.base.dialog;

import com.blankj.utilcode.util.ToastUtils;
import com.peng.commonlib.dialog.TerminalDialogFragment;
import com.peng.commonlib.manager.DialogFactory;
import com.peng.commonlib.ui.base.view.MVPTerminalProgress;
import com.peng.commonlib.utils.AppManager;

/**
 * create by Mr.Q on 2019/3/14.
 * 类介绍：
 */
public abstract class AbsTerminalProgressDialogFragment extends AbsProgressDialogFragment implements MVPTerminalProgress {


    @Override
    public void showErrorMsg(CharSequence msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showUnauthorized(String msg) {
//        AppManager.exitMQTT(requireContext());
        DialogFactory.terminalDialogFragment(getChildFragmentManager(), msg, new TerminalDialogFragment.OnTerminalDialogListener() {
            @Override
            public void callback() {
                AppManager.backToLogin(requireContext());
            }
        });
    }

    @Override
    public void showInvalidAccountRecord() {
        TerminalDialogFragment terminalDialogFragment = new TerminalDialogFragment();
        terminalDialogFragment.showOnWindow(getChildFragmentManager(), TerminalDialogFragment.class.getName());
    }
}