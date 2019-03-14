package com.peng.commonlib.ui.base.view.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Mr.Q on 2019/2/19.
 * 描述：
 * Dagger抽象类，继承自最顶层基类，扩展以支持Dagger注入
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
 * feature: Dagger2依赖注入支持，故需要在FragmentModule中进行声明，如下：
 *      @ContributesAndroidInjector()
 *      abstract fun contributeXXXFragment(): XXXFragment
 *
 */
abstract class AbsDaggerFragment extends AbsBaseFragment implements HasSupportFragmentInjector {

    @Inject
    public DispatchingAndroidInjector<Fragment> childFragmentInjector;

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
