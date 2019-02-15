package com.peng.commonlib.test;

import android.os.Bundle;

import com.peng.commonlib.R;
import com.peng.commonlib.base.AbsDaggerActivity;

import javax.inject.Inject;

public class MainActivity extends AbsDaggerActivity {

    @Inject
    TestClass mTestClass; // 声明对象，注入到 MainActivity 中即可使用

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTestClass.print();
    }

    @Override
    protected void injection() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


}
