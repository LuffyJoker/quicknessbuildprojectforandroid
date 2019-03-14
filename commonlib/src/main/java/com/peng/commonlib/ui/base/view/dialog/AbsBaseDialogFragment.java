package com.peng.commonlib.ui.base.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;

import com.peng.commonlib.ui.base.view.activity.AbsBaseActivity;
import com.peng.commonlib.utils.SoftKeyboardUtils;
import com.peng.dglib.BaseDialogFragment;


/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 *
 * 对话框 Fragment 最顶层抽象类，继承自 Library 的 BaseDialogFragment，扩展以支持额外配置
 * 注意：实现类请覆写 replaceOptions 方法、或 modifyOptions 方法(荐)、或使用时DSL语法以提供 layoutId，否则使用默认的进度条layout
 * 注意：实现类可以覆写replaceOptions方法、或modifyOptions方法(荐)、或使用时DSL语法，以配置Dialog的各种属性，如动画、commit方式、显示位置等
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
public abstract class AbsBaseDialogFragment extends BaseDialogFragment {

    private AbsBaseActivity parentActivity = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (AbsBaseActivity)context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //当销毁后，去掉和上下文的关联
        parentActivity = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在该生命周期执行 ARouter 的注入，原因如下：
        // Fragment 会存在只重建视图，不重建实例的情况。这种情况下，ARouter 的注入不需要再次执行，故放在 onCreate 之后，onCreateView 之前
        ARouter.getInstance().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 默认不弹出软键盘
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // 注册触摸事件，实现点击软键盘区域外，隐藏软键盘的效果
        getDialog().getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SoftKeyboardUtils.dispatchTouchEvent(getDialog(), event);
                return false;
            }
        });

        initView(savedInstanceState);

        initData(savedInstanceState);
    }



    private AbsBaseActivity getBaseActivity(){
        return parentActivity;
    }

    /**
     * 初始化 View
     *
     * @param savedInstanceState
     * @return
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);
}
