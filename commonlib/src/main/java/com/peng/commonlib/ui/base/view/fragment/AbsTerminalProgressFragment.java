package com.peng.commonlib.ui.base.view.fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.peng.commonlib.dialog.TerminalDialogFragment;
import com.peng.commonlib.manager.DialogFactory;
import com.peng.commonlib.ui.base.view.MVPTerminalProgress;
import com.peng.commonlib.utils.AppManager;

/**
 * create by Mr.Q on 2019/3/14.
 * 类介绍：
 * 终止App运行抽象类，继承自进度条抽象类，扩展以支持统一终止App运行
 *
 * feature: 布局id模板方法
 *
 * feature: parentActivity持有
 *
 * feature: ARouter注入
 *
 * feature: 键盘事件处理
 *
 * feature: initView模板方法
 *
 * feature: initData模板方法
 *
 * feature: Dagger2依赖注入支持，且支持MVP，故需要在FragmentModule中进行声明，如下：
 *      @ContributesAndroidInjector(modules = [XXXFragmentModule::class])
 *      abstract fun contributeXXXFragment(): XXXFragment
 *
 * feature: 进度条显隐
 *
 * feature: 提供强制退出对话框以即时终止App运行
 */
public abstract class AbsTerminalProgressFragment extends AbsProgressFragment implements MVPTerminalProgress {

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