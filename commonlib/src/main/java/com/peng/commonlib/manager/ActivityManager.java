package com.peng.commonlib.manager;

import android.app.Activity;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Mr.Q on 2019/2/16.
 * 描述：
 *      Activity 管理类，用于管理项目中所有的 Activity
 */
public class ActivityManager {

    private static HashSet<Activity> hashSet = new HashSet<>();

    /**
     * Activity 加入管理器中
     *
     * @author pq
     * create at 2019/2/16
     */
    public static void addActivity(Activity activity) {
        hashSet.add(activity);
    }

    /**
     * 退出程序，通过管理器，结束所有Activity
     *
     * @author pq
     * create at 2019/2/16
     */
    public static void exit() {
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            Activity activity = (Activity) iterator.next();
            activity.finish();
        }
    }

    /**
     * 将 Activity 移除管理器中
     *
     * @author pq
     * create at 2019/2/16
     */
    public static  void removeActivity(Activity activity) {
        if (hashSet.contains(activity)) {
            hashSet.remove(activity);
        }
    }

}
