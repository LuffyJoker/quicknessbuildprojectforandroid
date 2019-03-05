package com.peng.commonlib.base.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.peng.commonlib.base.fragment.AbsBaseFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Mr.Q on 2019/2/19.
 * 描述：
 *      1、Fragment 的 Dagger 抽象类，继承自最顶层基类，扩展以支持 Dagger 注入
 *      2、Dagger2 依赖注入支持，所以需要在 FragmentModule 中进行声明，如下：
 *          @ContributesAndroidInjector()
 *          abstract XXXFragment contributeXXXFragment();
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
