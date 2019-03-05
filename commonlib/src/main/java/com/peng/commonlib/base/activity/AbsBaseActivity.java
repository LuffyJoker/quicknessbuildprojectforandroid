package com.peng.commonlib.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.peng.commonlib.base.fragment.AbsBaseFragment;
import com.peng.commonlib.manager.ActivityManager;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

/**
 * Created by Mr.Q on 2019/2/16.
 * 描述：
 */
public abstract class AbsBaseActivity extends RxAppCompatActivity implements AbsBaseFragment.CallBack {

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
    protected void injection(){
//        ARouter.getInstance().inject(this);
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
