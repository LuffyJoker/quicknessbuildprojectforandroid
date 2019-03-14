package com.peng.commonlib.ui.base.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.peng.commonlib.manager.ActivityManager;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Mr.Q on 2019/2/16.
 * 描述：
 *  1、Dagger 抽象类，继承自最顶层基类，扩展以支持 Dagger 注入
 *  2、Dagger2 依赖注入支持，故需要在 ActivityModule 中进行声明，如下：
 *     @ContributesAndroidInjector()
 *     abstract fun contributeXXXActivity(): XXXActivity
 */
public abstract class AbsDaggerActivity extends AbsBaseActivity implements HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> frameworkFragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        ActivityManager.addActivity(this); //加入activity控制集合
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.removeActivity(this); //移除activity控制集合
        super.onDestroy();
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return supportFragmentInjector;
    }

    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return frameworkFragmentInjector;
    }
}
