package com.peng.commonlib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mr.Q on 2019/2/16.
 * 描述：
 */
public abstract class AbsBaseFragment extends Fragment {

    // Fragment 接入 Activity 的方法回调
    public interface CallBack {
        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    private AbsBaseActivity parentActivity = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (AbsBaseActivity) context;
        parentActivity.onFragmentAttached();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(getLayoutId(), container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 注入
        injection(view);

        // 在该生命周期执行View自定义初始化，原因如下：
        // androidKTX的view导入需要用到getView()方法来查找，所以必须在onCreateView返回之后才能获取到，否则会报NullPointerException
        initView(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 在该生命周期执行 data 自定义初始化
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
     * 三方框架注入
     *
     * @author pq
     * create at 2019/2/16
     */
    protected abstract void injection(View view);

    /**
     * 提供视图布局Id, 如果 [.initView] 返回 0, 框架则不会调用 [Activity.setContentView]
     *
     * @author pq
     * create at 2019/2/16
     */
    protected abstract LayoutInflater getLayoutId();
}
