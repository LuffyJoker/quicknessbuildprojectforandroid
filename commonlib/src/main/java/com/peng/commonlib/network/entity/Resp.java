package com.peng.commonlib.network.entity;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 *      数据实体
 */
public class Resp<T> {
    /**
     * 状态码
     */
    public int code;

    /**
     * 状态消息
     */
    public String msg;

    /**
     * 数据实体
     */
    public T data = null;
}
