package com.peng.commonlib.daggerinject.component;

import com.peng.commonlib.BaseApplication;
import com.peng.commonlib.daggerinject.module.AppModule;
import com.peng.commonlib.daggerinject.module.DatabaseModule;
import com.peng.commonlib.daggerinject.module.NetworkModule;
import com.peng.commonlib.daggerinject.module.PreferenceModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Mr.Q on 2019/2/15.
 * 描述：
 *      1、注入了三个 module，这种方式省略了传统方式中的下列步骤
 *          @Component interface MainActivityComponent {
 *              void inject(MainActivity activity);
 *          }
 *      2、
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,  // 用于绑定扩展的组件，如v4
        AndroidInjectionModule.class, // 用于绑定普通的组件
        AppModule.class,// App 层相关对象提供者
        PreferenceModule.class, // sharePreference 层相关依赖提供者
        NetworkModule.class, // 网络层依赖提供者
        DatabaseModule.class}) //数据库层
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseApplication>{}
}
