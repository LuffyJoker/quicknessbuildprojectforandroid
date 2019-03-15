package com.peng.commonlib.dialog;

import android.os.Bundle;

import com.blankj.utilcode.util.ConvertUtils;
import com.peng.commonlib.ui.base.view.dialog.AbsBaseDialogFragment;
import com.peng.dglib.other.DialogFragmentOptions;


/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 *      加载数据时，显示的提示对话框
 */
public class ProgressDialogFragment extends AbsBaseDialogFragment {


    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected DialogFragmentOptions getDialogFragmentOptions() {
        DialogFragmentOptions options = new DialogFragmentOptions();
        options.width = ConvertUtils.dp2px(200);
        options.height = ConvertUtils.dp2px(200);
        options.touchCancel = false;
        options.backCancel = false;
        return options;
    }
}
