package com.peng.commonlib.ui;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Mr.Q on 2019/2/16.
 * 描述：
 */
public class TestClass {

    private static final String TAG = "TestClass";

    @Inject //构造器注入
    public TestClass() {

    }

    public void print() {
        Log.d(TAG, "This is a TestClass");
    }
}
