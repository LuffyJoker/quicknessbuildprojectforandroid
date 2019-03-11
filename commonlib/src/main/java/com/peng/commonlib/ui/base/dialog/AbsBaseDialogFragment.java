package com.peng.commonlib.ui.base.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.peng.commonlib.ui.base.activity.AbsBaseActivity;


/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
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
        // 在该生命周期执行ARouter的注入，原因如下：
        // Fragment会存在只重建视图，不重建实例的情况。这种情况下，ARouter的注入不需要再次执行，故放在onCreate之后，onCreateView之前
//        ARouter.getInstance().inject(this);
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
//                HolderSoftKeyboardUtils.dispatchTouchEvent(dialog, event);
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
