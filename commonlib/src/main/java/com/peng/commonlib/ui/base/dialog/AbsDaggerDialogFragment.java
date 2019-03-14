package com.peng.commonlib.ui.base.dialog;

import android.content.Context;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 *
 * Dagger 抽象类，继承自最顶层基类，扩展以支持 Dagger 注入
 * 注意：实现类请覆写 replaceOptions 方法、或 modifyOptions 方法(荐)、或使用时 DSL 语法以提供 layoutId，否则使用默认的进度条 layout
 * 注意：实现类可以覆写 replaceOptions 方法、或 modifyOptions 方法(荐)、或使用时 DSL 语法，以配置 Dialog 的各种属性，如动、commit 方式、显示位置等
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
 * feature: Dagger2依赖注入支持，故需要在DialogModule中进行声明，如下：
 *      @ContributesAndroidInjector()
 *      abstract fun contributeXXXDialogFragment(): XXXDialogFragment
 *
 */
public abstract class AbsDaggerDialogFragment extends AbsBaseDialogFragment implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }
}
