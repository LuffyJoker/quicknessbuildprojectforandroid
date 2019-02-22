package com.peng.commonlib.daggerinject.module;

import com.peng.commonlib.receiver.NetworkChangeReceiver;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Mr.Q on 2019/2/19.
 * 描述：
 *      BroadcastReceiver 依赖提供者，由 AppModule 引用
 */
@Module
public abstract class ReceiverModule {

    @ContributesAndroidInjector()
    abstract NetworkChangeReceiver contributeNetworkChangeReceiver();
}