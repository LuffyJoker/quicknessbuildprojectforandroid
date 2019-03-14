package com.peng.commonlib.dialog;

import android.os.Bundle;

import com.peng.commonlib.ui.base.view.dialog.AbsDaggerDialogFragment;


/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 */
public class SpecChanageConfirmDialogFragment extends AbsDaggerDialogFragment {

    public static final String CONFIRM_MSG = "confirm_msg"; //提示内容
    public static final String CONFIRM_QR = "confirm_qr"; //确认按钮
    public static final String CONFIRM_IS_RED_QR_BUTTON = "CONFIRM_IS_RED_QR_BUTTON"; //是否着重标记确认按钮


    //回调，参数表示是否保存
//    private var guestNumDetermineListener: ((Boolean) -> Unit)? = null

//    @JvmField
//    @Autowired(name = CONFIRM_MSG)
//    var msg:String = ""
//    @JvmField
//    @Autowired(name = CONFIRM_QR)
//    var qr:String = ""
//    @JvmField
//    @Autowired(name = CONFIRM_IS_RED_QR_BUTTON)
//    var isRedButton:Boolean = false
//
//    override var compileOverrideOptions: (DialogOptions.() -> Unit)? = {
//        layoutId = R.layout.spec_chanage_confirm_dialog_fragment
//        width = HolderConvertUtls.dp2px(280f)
//        height = HolderConvertUtls.dp2px(400f)
//        touchCancel = false
//        outCancel = false
//    }


    @Override
    protected void initView(Bundle savedInstanceState) {
//        btn_confirm_determine.text = qr
//        btn_confirm_determine.setOnClickListener {
//            guestNumDetermineListener?.invoke(true)
//            dismiss()
//        }
//        btn_confirm_cencle.setOnClickListener {
//            guestNumDetermineListener?.invoke(false)
//            dismiss()
//        }
//        if(isRedButton){
//            btn_confirm_cencle.setBackgroundResource(R.drawable.global_btn_blue_selector)
//            btn_confirm_determine.setBackgroundResource(R.drawable.global_btn_red_selector)
//        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        tv_msg.text = msg
    }

//
//    fun setStatusDetermineListener(isSaveListener: (Boolean) -> Unit) {
//        this.guestNumDetermineListener = isSaveListener
//    }
}
