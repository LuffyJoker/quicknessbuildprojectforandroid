package com.peng.commonlib.daggerinject.module;

import com.peng.commonlib.ui.DemoActivity;
import com.peng.commonlib.ui.demo.DemoModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Mr.Q on 2019/2/15.
 * 描述：
 *  @Module 用于提供类的实例
 *  Activity 依赖提供者，由 AppModule 引用
 */

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {DemoModule.class})
    abstract DemoActivity mainActivity();  // 绑定 xxxActivity，即将 XXXActivity 的实例绑定到注入框架中

}
