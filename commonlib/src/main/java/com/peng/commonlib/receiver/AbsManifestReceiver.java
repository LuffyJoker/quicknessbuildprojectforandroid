package com.peng.commonlib.receiver;

import dagger.android.DaggerBroadcastReceiver;

/**
 * Created by Mr.Q on 2019/2/19.
 * 描述：
 *  继承自 DaggerBroadcastReceiver，目前无扩展，仅为了撰写类注释，以表明必须在AndroidManifest.xml中注册
 *  注意：子类请一定在AndroidManifest.xml中注册，否则Dagger2无法执行Injection，并且会抛出错误
 */
abstract class AbsManifestReceiver extends DaggerBroadcastReceiver {
}
