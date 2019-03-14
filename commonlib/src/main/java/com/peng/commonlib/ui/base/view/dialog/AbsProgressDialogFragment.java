package com.peng.commonlib.ui.base.view.dialog;

import com.peng.commonlib.dialog.ProgressDialogFragment;
import com.peng.commonlib.ui.base.view.MVPProgressView;

/**
 * create by Mr.Q on 2019/3/14.
 * 类介绍：
 *
 * 带进度条抽象类，继承自 Dagger 抽象类，扩展以支持耗时任务中的进度条显隐
 * 注意：实现类请覆写 replaceOptions 方法、或 modifyOptions 方法(荐)、或使用时DSL语法以提供 layoutId，否则使用默认的进度条 layout
 * 注意：实现类可以覆写 replaceOptions 方法、或 modifyOptions 方法(荐)、或使用时DSL语法，以配置 Dialog 的各种属性，如动画、commit 方式、显示位置等
 *
 * feature: parentActivity 持有
 *
 * feature: ARouter 注入
 *
 * feature: 键盘事件处理
 *
 * feature: initView 模板方法
 *
 * feature: initData 模板方法
 *
 * feature: Dagger2 依赖注入支持，且支持 MVP，故需要在 DialogModule 中进行声明，如下：
 *      @ContributesAndroidInjector(modules = [XXXDialogFragmentModule::class])
 *      abstract fun contributeXXXDialogFragment(): XXXDialogFragment
 *
 * feature: 进度条显隐
 */
public abstract class AbsProgressDialogFragment extends AbsMVPDialogFragment implements MVPProgressView {

    private AbsBaseDialogFragment absBaseDialogFragment;

    @Override
    public void showProgress() {
        ProgressDialogFragment absBaseDialogFragment = new ProgressDialogFragment();
        this.absBaseDialogFragment = absBaseDialogFragment;
        absBaseDialogFragment.showOnWindow(getChildFragmentManager(),ProgressDialogFragment.class.getName());
    }

    @Override
    public void hideProgress() {
        this.absBaseDialogFragment.dismiss();
        this.absBaseDialogFragment = null;
    }

}