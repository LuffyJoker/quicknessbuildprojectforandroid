package com.peng.commonlib.ui.base.view.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.peng.commonlib.ui.base.view.MVPView;

/**
 * create by Mr.Q on 2019/3/14.
 * 类介绍：
 *
 * MVP 抽象类，继承自 Dagger 抽象类，扩展以支持 MVP 分层架构
 * 注意：实现类请覆写replaceOptions方法、或modifyOptions方法(荐)、或使用时DSL语法以提供 layoutId，否则使用默认的进度条 layout
 * 注意：实现类可以覆写replaceOptions方法、或modifyOptions方法(荐)、或使用时DSL语法，以配置 Dialog 的各种属性，如动画、commit方式、显示位置等
 *
 * feature: parentActivity 持有
 *
 * feature: ARouter注入
 *
 * feature: 键盘事件处理
 *
 * feature: initView 模板方法
 *
 * feature: initData 模板方法
 *
 * feature: Dagger2 依赖注入支持，且支持 MVP，故需要在 DialogModule 中进行声明，如下：
 *      @ContributesAndroidInjector(modules = [XXXDialogFragmentModule.class])
 *      abstract fun contributeXXXDialogFragment(): XXXDialogFragment
 */
public abstract class AbsMVPDialogFragment extends AbsDaggerDialogFragment implements MVPView {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 在该生命周期执行View向Presenter的attach操作，原因如下：
        // 一旦该方法执行，那么Presenter就能拿到View的引用，就有可能访问View的方法执行UI操作
        // 而此时如果视图尚未创建成功，会发生异常。故放在onViewCreated之后执行，保证View的UI操作一定合法
        attachPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 在该生命周期执行View向Presenter的detach操作，原因如下：
        // 在视图销毁前，及时解除Presenter对View的引用
        // 否则，等到视图销毁后，一旦访问View的方法执行UI操作，就会发生异常。故放在onDestroyView之前执行，保证View的UI操作一定合法
        detachPresenter();
    }

    /**
     * 链接Presenter
     */
    abstract void attachPresenter();

    /**
     * 分离Presenter
     */
    abstract void detachPresenter();

}
