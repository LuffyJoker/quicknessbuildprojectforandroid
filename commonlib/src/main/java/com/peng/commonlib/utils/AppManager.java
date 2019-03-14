package com.peng.commonlib.utils;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.peng.commonlib.manager.ActivityManager;
import com.peng.commonlib.routing.RoutingConstants;

/**
 * create by Mr.Q on 2019/3/14.
 * 类介绍：
 */
public class AppManager {

//    public static void exitMQTT(Context context){
//        // 资源释放
//        context.stopService(Intent(context, PrinterService.))
//    }

    /**
     * 退回登录界面，不关闭 mqtt 连接
     */
    public static void backToLoginMqttHolding(Context context){
        ActivityManager.exit();
        ARouter.getInstance().build(RoutingConstants.ROUTING_LOGIN_ACTIVITY)
                .navigation();
    }

    /**
     * 退回登录界面
     */
    public static void backToLogin(Context context){
        ActivityManager.exit();
        ARouter.getInstance().build(RoutingConstants.ROUTING_LOGIN_ACTIVITY).navigation();
        // 资源释放
//        context.stopService(new Intent(context, PrinterService.java));
    }

    public static void exit(Context context) {

        // 资源释放
//        context.stopService(new Intent(context, PrinterService.java));

        // 清空activity栈，然后退出app
        AppUtils.exitApp();
    }
}
