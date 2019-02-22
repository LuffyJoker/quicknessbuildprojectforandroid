package com.peng.commonlib.test;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.peng.commonlib.R;
import com.peng.commonlib.base.AbsDaggerActivity;
import com.peng.commonlib.database.AppDatabase;
import com.peng.commonlib.test.contract.TestContract;

import javax.inject.Inject;

public class MainActivity extends AbsDaggerActivity {

    @Inject
    AppDatabase mAppDatabase;

    @Inject
    TestClass mTestClass; // 声明对象，注入到 MainActivity 中即可使用

    @Inject
    TestContract.Presenter<TestContract.View, TestContract.Interactor> presenter;

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


    public void click(View view) {
        LogUtils.d("123");
        presenter.queryUserByUserID(123L,123);
    }
}
