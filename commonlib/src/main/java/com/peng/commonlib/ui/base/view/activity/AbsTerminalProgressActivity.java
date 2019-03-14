package com.peng.commonlib.ui.base.view.activity;

import com.blankj.utilcode.util.ToastUtils;
import com.peng.commonlib.dialog.TerminalDialogFragment;
import com.peng.commonlib.manager.DialogFactory;
import com.peng.commonlib.ui.base.view.MVPTerminalProgress;
import com.peng.commonlib.utils.AppManager;

/**
 * create by Mr.Q on 2019/3/14.
 * 类介绍：
 *  1、终止 App 运行抽象类，继承自进度条抽象类，扩展以支持统一终止 App 运行
 *  2、提供强制退出对话框以即时终止App运行
 *  3、Dagger2依赖注入支持，且支持MVP，故需要在ActivityModule中进行声明，如下：
 *       @ContributesAndroidInjector(modules = [XXXActivityModule::class])
 *       abstract fun contributeXXXActivity(): XXXActivity
 */
public abstract class AbsTerminalProgressActivity extends AbsProgressActivity implements MVPTerminalProgress {


    @Override
    public void showErrorMsg(CharSequence msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showUnauthorized(String msg) {
//        AppManager.exitMQTT(this);
        DialogFactory.terminalDialogFragment(getSupportFragmentManager(), msg, new TerminalDialogFragment.OnTerminalDialogListener() {
            @Override
            public void callback() {
                AppManager.backToLogin(AbsTerminalProgressActivity.this);
            }
        });
    }

    @Override
    public void showInvalidAccountRecord() {
        TerminalDialogFragment terminalDialogFragment = new TerminalDialogFragment();
        terminalDialogFragment.showOnWindow(getSupportFragmentManager(),TerminalDialogFragment.class.getName());
    }
}
