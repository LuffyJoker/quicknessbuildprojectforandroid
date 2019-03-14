package com.peng.commonlib.ui;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.peng.commonlib.R;
import com.peng.commonlib.data.database.AppDatabase;
import com.peng.commonlib.data.network.entity.DemoEntity;
import com.peng.commonlib.data.network.entity.Resp;
import com.peng.commonlib.routing.RoutingConstants;
import com.peng.commonlib.rx.transformer.TransformerFactory;

import com.peng.commonlib.ui.base.view.activity.AbsDaggerActivity;
import com.peng.commonlib.ui.base.view.activity.AbsTerminalProgressActivity;
import com.peng.commonlib.ui.demo.DemoContract;

import javax.inject.Inject;

import io.reactivex.functions.Action;

public class DemoActivity extends AbsTerminalProgressActivity implements DemoContract.View {

    @Inject
    AppDatabase mAppDatabase;

    @Inject
    DemoContract.Presenter<DemoContract.View, DemoContract.Interactor> presenter;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void attachPresenter() {
        presenter.onAttach(this);
    }

    @Override
    protected void detachPresenter() {
        presenter.onDetach();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public void click(View view) {
        // 数据库使用
        presenter.queryUserByUserID(123L, 123)
                .compose(TransformerFactory.ioToMain())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
//                        LogUtils.d("执行订阅");
                    }
                });

        presenter.fetchBindingState();

//        ARouter.getInstance()
//                .build(RoutingConstants.ROUTING_BINDING_ACTIVITY)
//                .withTransition(R.anim.popumenu_animation_show, R.anim.popumenu_animation_hide)
//                .navigation(this);
//        finish();
    }

    @Override
    public void success(Resp<DemoEntity> resp) {
        LogUtils.d(resp.tdata.getStoreName());
    }

    @Override
    public void fail(int code, String msg) {
        LogUtils.d("失败：" + code + "\n" + "失败原因：" + msg);
    }


}
