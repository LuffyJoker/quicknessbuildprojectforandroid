package com.peng.commonlib.manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.FragmentUtils;
import com.peng.commonlib.dialog.SpecChanageConfirmDialogFragment;
import com.peng.commonlib.dialog.TerminalDialogFragment;
import com.peng.commonlib.routing.RoutingConstants;
import com.peng.dglib.BaseDialogFragment;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 */
public class DialogFactory {

    /**
     * 是否对话框提
     * [msg] 显示的消息
     * [qr] 确认按钮的文字
     * [isRedQrButton] 是否将确认按钮着重标记为红色
     * [status] 按钮的点击回调
     */
    public static BaseDialogFragment specConfirmDialogFragment(String msg, String qr, boolean isRedQrButton) {
//        val baseDialogFragment = ARouter.getInstance()
//                .build(RoutingConstants.SPEC_CHANAGE_CONFIRM_DIALOG_FRAGMENT)
//                .withString(SpecChanageConfirmDialogFragment.CONFIRM_MSG, msg)
//                .withString(SpecChanageConfirmDialogFragment.CONFIRM_QR, qr)
//                .withBoolean(SpecChanageConfirmDialogFragment.CONFIRM_IS_RED_QR_BUTTON, isRedQrButton)
//                .navigation() as SpecChanageConfirmDialogFragment
//        return baseDialogFragment.apply { setStatusDetermineListener(status) }
        return new SpecChanageConfirmDialogFragment();
    }

    /**
     * 一个按钮的 dialog
     */
    public static void terminalDialogFragment(FragmentManager fragmentManager, String msg, TerminalDialogFragment.OnTerminalDialogListener listener) {
        Fragment fragment = FragmentUtils.findFragment(fragmentManager, TerminalDialogFragment.class);
        if (fragment == null) {
            TerminalDialogFragment terminalDialogFragment = (TerminalDialogFragment) ARouter.getInstance()
                    .build(RoutingConstants.ROUTING_TERMINAL_DIALOG_FRAGMENT)
                    .withString(TerminalDialogFragment.MSG, msg)
                    .navigation();
            terminalDialogFragment.setListener(listener);
            terminalDialogFragment.showOnWindow(fragmentManager, terminalDialogFragment.getClass().getName());
        }
    }
}
