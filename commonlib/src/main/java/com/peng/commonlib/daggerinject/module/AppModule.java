package com.peng.commonlib.daggerinject.module;

import android.app.Application;
import android.content.Context;

import com.peng.commonlib.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Q on 2019/2/19.
 * 描述：
 */
@Module(includes = {ActivityModule.class, FragmentModule.class, DialogModule.class, ServiceModule.class, ReceiverModule.class, DatabaseModule.class})
public class AppModule {
    @Singleton
    @Provides
    public Application provideApplication(BaseApplication app) {
        return app;
    }

    @Singleton
    @Provides
    public Context provideContext(BaseApplication app) {
        return app;
    }




}
