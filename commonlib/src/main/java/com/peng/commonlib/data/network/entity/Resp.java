package com.peng.commonlib.data.network.entity;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 *      1、http 请求基础实体封装，T 是真正返回的结果
 *      2、可根据服务器返回的具体的公共字段对该类进行适当地修改
 *      3、创建用于映射Json的实体时，值需要创建 tdata 字段下的 Json 对应的实体即可
 *      4、封装意图：为了统一处理状态码，在异常时，统一提示状态消息
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
     * 时间点
     */
    public int now;

    /**
     * 数据实体
     */
    public T tdata = null;
}
