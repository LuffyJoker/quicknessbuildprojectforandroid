package com.peng.commonlib.ui;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.peng.commonlib.R;
import com.peng.commonlib.data.database.AppDatabase;
import com.peng.commonlib.routing.RoutingConstants;
import com.peng.commonlib.rx.threadswitch.TransformerFactory;
import com.peng.commonlib.ui.base.activity.AbsDaggerActivity;
import com.peng.commonlib.ui.demo.DemoContract;

import javax.inject.Inject;

import io.reactivex.functions.Action;

public class DemoActivity extends AbsDaggerActivity implements DemoContract.View {

    @Inject
    AppDatabase mAppDatabase;


    @Inject
    DemoContract.Presenter<DemoContract.View, DemoContract.Interactor> presenter;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        presenter.onAttach(this);
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
//                        LogUtils.d("执行订阅");
                    }
                });

        presenter.fetchBindingState();

        ARouter.getInstance()
                .build(RoutingConstants.ROUTING_BINDING_ACTIVITY)
                .withTransition(R.anim.popumenu_animation_show, R.anim.popumenu_animation_hide)
                .navigation(this);
//        finish();
    }

    @Override
    public void success() {

    }

    @Override
    public void fail() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorMsg(CharSequence msg) {

    }

    @Override
    public void showUnauthorized(String msg) {

    }

    @Override
    public void showInvalidAccountRecord() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
