package com.peng.commonlib.daggerinject.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.RxSharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 *      SharedPreference 依赖提供者，由 AppComponent 依赖
 */
@Module
public class PreferenceModule {

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    RxSharedPreferences provideRxSharedPreferences(SharedPreferences sharedPreferences){
        return RxSharedPreferences.create(sharedPreferences);
    }

//    @Singleton
//    @Provides
//    @Named(PrefKeyConstant.NETWORK_IS_AVAILABLE)
//    PreferenceAdapter<Boolean> provideNetworkIsAvailablePreference(RxSharedPreferences rxSharedPreferences) {
//        Preference<Boolean> preference = rxSharedPreferences.getBoolean(PrefKeyConstant.NETWORK_IS_AVAILABLE, false);
//        return RxPreferenceAdapter(preference);
//    }

}
