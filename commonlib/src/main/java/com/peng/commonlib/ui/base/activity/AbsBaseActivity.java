package com.peng.commonlib.ui.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.alibaba.android.arouter.launcher.ARouter;
import com.peng.commonlib.manager.ActivityManager;
import com.peng.commonlib.ui.base.fragment.AbsBaseFragment;
import com.peng.commonlib.utils.SoftKeyboardUtils;

/**
 * Created by Mr.Q on 2019/2/16.
 * 描述：
 * activity 最顶层基类，继承自 supportV7 包 AppCompatActivity
 *
 * feature: 布局 id 模板方法
 *
 * feature: 三方框架注入模板方法
 *
 * feature: ARouter 注入
 *
 * feature: 键盘事件处理
 *
 * feature: initView 模板方法
 *
 * feature: initData 模板方法
 */
public abstract class AbsBaseActivity extends AppCompatActivity implements AbsBaseFragment.CallBack {

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        // 用于第三方框架的注入
        injection();

        // 键盘事件注册
        SoftKeyboardUtils.registerTouchEvent(this, null);

        // 初始化控件
        initView(savedInstanceState);

        // 初始化数据
        initData(savedInstanceState);

    }

    /**
     * 初始化数据
     *
     * @author pq
     * create at 2019/2/16
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化各种控件
     *
     * @author pq
     * create at 2019/2/16
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 用于第三方框架注入
     *
     * @author pq
     * create at 2019/2/16
     */
    protected void injection() {
        ARouter.getInstance().inject(this);
    }

    /**
     * 提供视图布局Id, 如果 [.initView] 返回 0, 框架则不会调用 [Activity.setContentView]
     *
     * @author pq
     * create at 2019/2/16
     */
    protected abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        ActivityManager.removeActivity(this); //移除activity控制集合
        super.onDestroy();
    }

}
