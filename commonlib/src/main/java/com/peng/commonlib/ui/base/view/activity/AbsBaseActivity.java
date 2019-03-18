package com.peng.commonlib.ui.base.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.peng.commonlib.R;
import com.peng.commonlib.manager.ActivityManager;
import com.peng.commonlib.ui.base.view.fragment.AbsBaseFragment;
import com.peng.commonlib.utils.SoftKeyboardUtils;
import com.peng.dglib.BaseDialogFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Mr.Q on 2019/2/16.
 * 描述：
 * activity 最顶层基类，继承自 supportV7 包 AppCompatActivity
 * <p>
 * feature: 布局 id 模板方法
 * <p>
 * feature: 三方框架注入模板方法
 * <p>
 * feature: ARouter 注入
 * <p>
 * feature: 键盘事件处理
 * <p>
 * feature: initView 模板方法
 * <p>
 * feature: initData 模板方法
 */
public abstract class AbsBaseActivity extends AppCompatActivity implements AbsBaseFragment.CallBack {

    private Unbinder mUnbinder;

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

        // ButterKnife绑定
        mUnbinder = ButterKnife.bind(this);

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
    public void injection() {
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
        // ButterKnife解除视图绑定
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        mUnbinder = null;

        super.onDestroy();
    }

    /**
     * 使用 replace 方法设置 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void setFragmentOfReplace(@NonNull int containerViewId, AbsBaseFragment fragment) {
        setFragmentOfReplace(containerViewId, fragment, null);
    }

    /**
     * 使用 replace 方法设置 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void setFragmentOfReplace(@NonNull int containerViewId, AbsBaseFragment fragment, Bundle bundle) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment)
                .commit();
    }

    /**
     * 使用 add 方法添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     * @param tag
     */
    protected void setFragmentOfAdd(@NonNull int containerViewId, AbsBaseFragment fragment, String tag) {
        setFragmentOfAdd(containerViewId, fragment, null, tag, false);
    }


    /**
     * 使用 add 方法添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     * @param tag
     */
    protected void setFragmentOfAdd(@NonNull int containerViewId, AbsBaseFragment fragment, String tag, boolean showAnim) {
        setFragmentOfAdd(containerViewId, fragment, null, tag, showAnim);
    }


    /**
     * 为Activity中的容器设置Fragment
     *
     * @param containerViewId
     * @param fragment
     * @param bundle
     * @param tag
     */
    protected void setFragmentOfAdd(@NonNull int containerViewId, AbsBaseFragment fragment, Bundle bundle, String tag, boolean showAnim) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.addToBackStack(null);

        if(showAnim){
            transaction.setCustomAnimations(R.animator.fragment_slide_right_enter, R.animator.fragment_slide_right_exit, R.animator.fragment_slide_right_enter, R.animator.fragment_slide_right_exit);
        }

        //注意setCustomAnimations()方法必须在 add、remove、replace 调用之前被设置，否则不起作用。
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        transaction.add(containerViewId, fragment, tag);
        transaction.commit();
    }
}
