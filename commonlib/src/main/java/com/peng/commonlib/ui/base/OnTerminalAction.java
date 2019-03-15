package com.peng.commonlib.ui.base;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 *      1、全局的终止操作
 *      2、弹出终止对话框，回退到登录界面
 */
public interface OnTerminalAction {

    /**
     * 账号认证失败，可能是因为 token 失效了
     * @param msg
     */
    void showUnauthorized(String msg);

    /**
     * 账户不可用
     */
    void showInvalidAccountRecord();
}
