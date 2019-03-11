package com.peng.commonlib.ui.base.exception;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 */
public class RespIgnoreException extends RuntimeException{

    private int mCode;
    private String mMsg;

    public RespIgnoreException(int code,String msg){
        super(msg);
    }
}
