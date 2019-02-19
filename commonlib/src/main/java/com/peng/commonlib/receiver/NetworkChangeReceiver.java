package com.peng.commonlib.receiver;

import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.NetworkUtils;

/**
 * Created by Mr.Q on 2019/2/19.
 * 描述：
 */
public class NetworkChangeReceiver extends AbsManifestReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (NetworkUtils.isConnected()) {
            return;
        }

        // 发送网络变化事件，如用RxBus发送
//        RxBus.INSTANCE.send(NetworkChangeEvent(isConnected))

    }
}
