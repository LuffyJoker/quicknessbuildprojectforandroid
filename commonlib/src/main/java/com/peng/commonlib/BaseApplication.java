package com.peng.commonlib;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import com.blankj.utilcode.util.Utils;
import com.peng.commonlib.daggerinject.component.DaggerAppComponent;
import com.peng.commonlib.network.HttpDebugUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


/**
 * Created by Mr.Q on 2019/2/15.
 * 描述：
 * 1、DaggerApplication 来源于支持库且有两个：
 * dagger.android.DaggerApplication：支持普通组件，不支持扩展（v4，v7）组件
 * dagger.android.support.DaggerApplication：支持扩展组件，不支持普通组件
 */
public class BaseApplication extends DaggerApplication {

    private static BaseApplication baseApplication;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

    public static Context getAppContext() {
        return baseApplication;
    }

    public static Resources getAppResources() {
        return baseApplication.getResources();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        baseApplication = this;

        // 初始化工具类
        Utils.init(this);

        //开启http调试模式(上线前记得关闭)
        if (BuildConfig.DEBUG) {
            HttpDebugUtils.init(true);
        }
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}
