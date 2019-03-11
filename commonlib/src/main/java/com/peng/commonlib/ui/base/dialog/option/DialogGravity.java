package com.peng.commonlib.ui.base.dialog.option;

import android.view.Gravity;

/**
 * create by Mr.Q on 2019/3/2.
 * 类介绍：
 * 枚举类，标记 dialog 的位置
 */
public enum DialogGravity {

    LEFT_TOP(Gravity.START | Gravity.TOP),

    CENTER_TOP(Gravity.CENTER_HORIZONTAL | Gravity.TOP),

    RIGHT_TOP(Gravity.END | Gravity.TOP),

    LEFT_CENTER(Gravity.START | Gravity.CENTER_VERTICAL),

    CENTER_CENTER(Gravity.CENTER),

    RIGHT_CENTER(Gravity.END | Gravity.CENTER_VERTICAL),

    LEFT_BOTTOM(Gravity.START | Gravity.BOTTOM),

    CENTER_BOTTOM(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM),

    RIGHT_BOTTOM(Gravity.END | Gravity.BOTTOM);

    // 成员变量
    private int layoutGravity;

    // 构造方法
    DialogGravity(int layoutGravity) {
        this.layoutGravity = layoutGravity;
    }

    public int getLayoutGravity() {
        return layoutGravity;
    }
}
