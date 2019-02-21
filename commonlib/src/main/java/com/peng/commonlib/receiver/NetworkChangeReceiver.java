package com.peng.commonlib.receiver;

import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.NetworkUtils;
import com.peng.commonlib.event.NetworkChangeEvent;
import com.peng.commonlib.rx.RxBus;

/**
 * Created by Mr.Q on 2019/2/19.
 * 描述：
 */
public class NetworkChangeReceiver extends AbsManifestReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        boolean isConnected = NetworkUtils.isConnected();

        if (NetworkUtils.isConnected()) {
            return;
        }

        // 发送网络变化事件，如用RxBus发送
        RxBus.getInstance().send(new NetworkChangeEvent(isConnected));

    }
}
