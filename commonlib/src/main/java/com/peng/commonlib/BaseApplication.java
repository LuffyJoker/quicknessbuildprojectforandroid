package com.peng.commonlib;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.facebook.stetho.Stetho;
import com.peng.commonlib.daggerinject.component.DaggerAppComponent;
import com.peng.commonlib.data.network.HttpDebugUtils;

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

        //开启 http 调试模式，是否使用 mock 数据，使用mock数据，则不会去服务器获取数据(上线前记得关闭)
        if (BuildConfig.DEBUG) {
            HttpDebugUtils.init(false);
        }

        // 初始化 Stetho，利用 chrome 查看网络请求
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        // arouter
        initARouter();
    }

    /**
     * 初始化阿里路由框架
     */
    private void initARouter() {
        if (AppUtils.isAppDebug()) {
            ARouter.openLog();    // 打印日志
            ARouter.openDebug();  // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
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
