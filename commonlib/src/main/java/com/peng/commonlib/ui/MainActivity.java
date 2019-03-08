package com.peng.commonlib.ui;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.peng.commonlib.R;
import com.peng.commonlib.base.activity.AbsDaggerActivity;
import com.peng.commonlib.database.AppDatabase;
import com.peng.commonlib.rx.threadswitch.TransformerFactory;
import com.peng.commonlib.ui.demo.TestContract;

import javax.inject.Inject;

import io.reactivex.functions.Action;

public class MainActivity extends AbsDaggerActivity {

    @Inject
    AppDatabase mAppDatabase;


    @Inject
    TestContract.Presenter<TestContract.View, TestContract.Interactor> presenter;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

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
        presenter.queryUserByUserID(123L, 123)
                .compose(TransformerFactory.ioToMain())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d("执行订阅");
                    }
                });
    }
}
