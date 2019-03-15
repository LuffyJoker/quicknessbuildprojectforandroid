package com.peng.commonlib.ui.base;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 *      OnDistributeAction:分发结果的操作
 *      使用该接口，根据 http 请求结果的状态码，对错误消息处理，账户认证失败等操作进行统一管理
 */
public interface OnDistributeAction extends OnErrorAction, OnTerminalAction {
}
