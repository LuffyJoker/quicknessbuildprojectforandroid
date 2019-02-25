package com.peng.commonlib;


import android.content.Context;
import android.content.res.Resources;

import com.peng.commonlib.daggerinject.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


/**
 * Created by Mr.Q on 2019/2/15.
 * 描述：
 *  1、DaggerApplication 来源于支持库且有两个：
 *      dagger.android.DaggerApplication：支持普通组件，不支持扩展（v4，v7）组件
 *      dagger.android.support.DaggerApplication：支持扩展组件，不支持普通组件
 */
public class BaseApplication extends DaggerApplication {

    private static BaseApplication baseApplication;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;

    }

    public static Context getAppContext() {
        return baseApplication;
    }

    public static Resources getAppResources() {
        return baseApplication.getResources();
    }
}
