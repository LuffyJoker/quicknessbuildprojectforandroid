package com.peng.commonlib.daggerinject.module;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Provides;

/**
 * create by Mr.Q on 2019/2/18.
 * 类介绍：
 */
public class NetworkModule {

    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                //修改:lcl 日期:2018.9.4
                //注意:此构建方式那么你必须显示的在你的对象中使用[@Expose]注解来暴露你的字段，
                // 否则不会被序列化
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
//                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
    }

    
}
