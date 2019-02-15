package com.peng.commonlib.daggerinject;

import com.peng.commonlib.BaseApplication;
import com.peng.commonlib.daggerinject.module.ActivityModule;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Mr.Q on 2019/2/15.
 * 描述：
 * 1、注入了三个 module，这种方式省略了传统方式中的下列步骤
 *
 * @Component interface MainActivityComponent {
 * void inject(MainActivity activity);
 * }
 */
@Component(modules = {
        ActivityModule.class,  // 用于绑定项目中的Activity
        AndroidSupportInjectionModule.class,  // 用于绑定扩展的组件，如v4
        AndroidInjectionModule.class})  // 用于绑定普通的组件
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseApplication>{}
}
