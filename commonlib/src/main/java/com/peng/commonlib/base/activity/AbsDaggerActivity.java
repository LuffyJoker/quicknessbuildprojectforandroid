package com.peng.commonlib.base.activity;

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
 */
public abstract class AbsDaggerActivity extends AbsBaseActivity implements HasFragmentInjector, HasSupportFragmentInjector {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        ActivityManager.addActivity(this); //加入activity控制集合
        super.onCreate(savedInstanceState);
    }

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> frameworkFragmentInjector;

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return supportFragmentInjector;
    }

    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return frameworkFragmentInjector;
    }
}
