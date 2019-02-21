package com.peng.commonlib.event;

/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 *      网络状态改变的事件定义
 */
public class NetworkChangeEvent {

    private boolean isConnected;

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public NetworkChangeEvent(boolean isConnected) {
        this.isConnected = isConnected;
    }
}
