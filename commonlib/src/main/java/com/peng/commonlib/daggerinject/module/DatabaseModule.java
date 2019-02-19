package com.peng.commonlib.daggerinject.module;

import com.peng.commonlib.constant.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Q on 2019/2/19.
 * 描述：
 *      1、数据库依赖提供者，由 AppComponent 引用
 */

@Module
public class DatabaseModule {
    @Singleton
    @Provides
//    @DatabaseName
    public String provideDatabaseName(){
        return AppConstants.APP_DB_NAME;
    }
}
