package com.peng.commonlib.ui.base.view.fragment;

import com.peng.commonlib.dialog.ProgressDialogFragment;

import com.peng.commonlib.ui.base.view.MVPProgressView;
import com.peng.commonlib.ui.base.view.dialog.AbsBaseDialogFragment;

/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 * 带进度条抽象类，继承自Dagger抽象类，扩展以支持耗时任务中的进度条显隐
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
 */
abstract class AbsProgressFragment extends AbsMVPFragment implements MVPProgressView {

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
