package com.peng.commonlib.ui.base;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 *      全局的终止操作
 */
public interface OnTerminalAction {
    void showUnauthorized(String msg);

    void showInvalidAccountRecord();
}
