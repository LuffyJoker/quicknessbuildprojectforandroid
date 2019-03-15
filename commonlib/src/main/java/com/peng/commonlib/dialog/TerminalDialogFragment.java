package com.peng.commonlib.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.blankj.utilcode.util.ConvertUtils;
import com.peng.commonlib.R;

import com.peng.commonlib.ui.base.view.dialog.AbsBaseDialogFragment;
import com.peng.commonlib.utils.AppManager;
import com.peng.dglib.other.DialogFragmentOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 *  1、强制App关闭的对话框
 */
public class TerminalDialogFragment extends AbsBaseDialogFragment {

    public static final String MSG = "terminal_msg";

    @BindView(R.id.tv_terminal_msg)
    TextView tvTerminalMsg;
    @BindView(R.id.btn_terminal_confirm)
    Button btnTerminalConfirm;
    Unbinder unbinder;

    @Autowired(name = MSG)
    String msg;

    public interface OnTerminalDialogListener {
        void callback();
    }

    private OnTerminalDialogListener listener = null;

    @Override
    protected DialogFragmentOptions getDialogFragmentOptions() {
        DialogFragmentOptions options = new DialogFragmentOptions();
        options.layoutId = R.layout.terminal_dialog_fragment;
        options.width = ConvertUtils.dp2px(320);
        options.height = ConvertUtils.dp2px(168);
        options.touchCancel = false;
        options.backCancel = false;
        return options;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tvTerminalMsg.setText(msg);
        btnTerminalConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.callback();
                } else {
                    AppManager.exit(requireContext());
                }
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    public void setListener(OnTerminalDialogListener listener) {
        this.listener = listener;
    }
}
