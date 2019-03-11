package com.peng.commonlib.ui.base;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 *  错误消息生成器接口类，定义了接口，负责 Throwable 到 ErrorMessage 的转换
 */
public interface OnErrorMessageGenerator {

    CharSequence generateErrorMessage(Throwable throwable);

}
