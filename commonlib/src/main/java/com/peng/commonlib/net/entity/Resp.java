package com.peng.commonlib.net.entity;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 *      数据实体
 */
public class Resp<T> {
    /**
     * 状态码
     */
    int code;

    /**
     * 状态消息
     */
    String msg;

    /**
     * 数据实体
     */
    T data = null;
}
