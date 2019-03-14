package com.peng.commonlib.ui.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.peng.commonlib.ui.base.activity.AbsBaseActivity;
import com.peng.commonlib.utils.SoftKeyboardUtils;


/**
 * Created by Mr.Q on 2019/2/16.
 * 描述：
 *
 * Fragment 最顶层抽象类，继承自supportV4 包 Fragment，扩展以支持额外配置
 *
 * feature: 布局id模板方法
 *
 * feature: parentActivity 持有
 *
 * feature: ARouter 注入
 *
 * feature: 键盘事件处理
 *
 * feature: initView 模板方法
 *
 * feature: initData 模板方法
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在该生命周期执行 ARouter 的注入，原因如下：
        // Fragment 会存在重建视图的情况。这种情况下，ARouter 的注入不需要再次执行，故放在 onCreate之后，onCreateView 之前
        ARouter.getInstance().inject(this);

        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 注入
        injection(view);

        // 在该生命周期执行View自定义初始化，原因如下：
        // androidKTX的view导入需要用到getView()方法来查找，所以必须在onCreateView返回之后才能获取到，否则会报NullPointerException
        initView(savedInstanceState);

        SoftKeyboardUtils.registerTouchEvent(getActivity(),null);
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
     * 提供视图布局Id, 如果 [.initView] 返回 0, 框架则不会调用 [Activity.setContentView]
     * @return
     */
    @LayoutRes
    abstract int layoutId();

    /**
     * 三方框架注入
     *
     * @author pq
     * create at 2019/2/16
     */
    protected abstract void injection(View view);
}
