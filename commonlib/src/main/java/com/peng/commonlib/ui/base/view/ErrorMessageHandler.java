package com.peng.commonlib.ui.base.view;

/**
 * Created by Mr.Q on 2019/3/14
 * 描述：错误消息处理器接口类，定义了接口，负责TErrorMessage到UI的响应
 */
public interface ErrorMessageHandler {
    void handleErrorMessage(CharSequence message);
}
