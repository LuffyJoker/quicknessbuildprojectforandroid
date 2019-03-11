package com.peng.commonlib.ui.base.dialog;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * create by Mr.Q on 2019/3/2.
 * 类介绍：
 */
public class BaseDialogFragment extends DialogFragment {
    /**
     * 根布局
     */
    private View rootView;

    /**
     * 绑定的activity
     */
    private AppCompatActivity mActivity;

    /**
     * * 执行顺序：2     * 绑定activity，不建议使用fragment里面自带的getActivity()
     */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    public AppCompatActivity getAppCompatActivity() {
        return mActivity;
    }

    /**
     * 描述：是否已经 dismiss，避免主动调用 dismiss 的时候与 onStop 中触发两次相同事件
     *
     * @author Mr.Q
     */
    private AtomicBoolean dismissed = new AtomicBoolean(false);

    /**
     * 描述：保存UI状态的标签
     *
     * @author Mr.Q
     */
    private String options = "options";

    /**
     * 描述：dialog 配置,所有配置都写在里面
     *
     * @author Mr.Q
     */
//    public DialogOptions dialogOptions = DialogOptions();

    /**
     * 描述：继承该基类时可以通过覆写该属性，以提供一个修改 DialogOptions 的操作
     *
     * @author Mr.Q
     */
//    protected open var
//    compileOverrideOptions:(DialogOptions.()->Unit)?=null
    /**
     * 使用时修改 DialogOptions 的操作
     */
//    private var runtimeOverrideOptions:(DialogOptions.()->Unit)?=null

    /**
     * 设置一个在使用时覆写DialogOptions的操作
     */
//    fun setRuntimeOverrideOptions(runtimeOverrideOptions:(DialogOptions.() ->Unit)?){
//        this.runtimeOverrideOptions = runtimeOverrideOptions
//    }

    /**
     * 懒加载，根据dialogOptions.duration来延迟加载实现懒加载（曲线救国）
     */
//    private void onLazy() {
//        convertView(ViewHolder(rootView), this);
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        compileOverrideOptions?.run { this.invoke(dialogOptions) }
//    }
}
