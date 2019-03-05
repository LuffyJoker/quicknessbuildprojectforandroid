package com.peng.commonlib.base.dialog.option;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.peng.commonlib.R;

/**
 * create by Mr.Q on 2019/3/2.
 * 类介绍：
 */
public class DialogOptions {

    /**
     * 布局文件id
     */
    @LayoutRes
    public int layoutId = R.layout.loading_layout;

    /**
     * Convert 监听
     */
//    internal var convertListener: ((viewHolder: ViewHolder) -> Unit)? = null

    /**
     * Window 属性
     */
    public int windowFeature = Window.FEATURE_NO_TITLE;

    /**
     * 是否允许取消对话框
     */
    boolean enableCancel = true;

    /**
     * 点击外部区域是否取消对话框
     */
    boolean touchOutsideCancel = true;

    /**
     * 系统级别对话框
     */
    boolean systemAlert = false;

    /**
     * 对话框宽度
     */
    int width = 0;

    /**
     * 对话框高度
     */
    int height = 0;

    /**
     * 背景透明度比例
     */
    float dimAmount = 0.3f;

    /**
     * 当 dialog 依附于window时的位置（默认居中）
     */
    int dialogGravity = DialogGravity.CENTER_CENTER.getLayoutGravity();

    /**
     * 当 dialog 依附于 view 时的横向位置（默认左对齐）
     */
    public HorizontalPosition horizontalPosition = HorizontalPosition.ALIGN_LEFT;

    /**
     * 当dialog依附于view时的纵向位置（默认在上方）
     */
    public VerticalPosition verticalPosition = VerticalPosition.ABOVE;

    /**
     * 当 dialog 依附在 view 上时横向的偏移量
     */
    public int horizontalOffset = 0;

    /**
     * 当dialog依附在view上时纵向的偏移量
     */
    public int verticalOffset = 0;

    /**
     * 依附于锚点显示时,计算出的对话框 X 坐标
     */
    public int dialogViewX = 0;

    /**
     * 依附于锚点显示时,计算出的对话框 Y 坐标
     */
    public int dialogViewY = 0;

    /**
     * 是否依附于锚点显示
     */
    public boolean showOnAnchor = false;

    /**
     * 依附于锚点显示时，计算 dialogViewX 和 dialogViewY
     */
    public void calculateDialogViewXY(View view) {
        //获取到dialogView的宽高
        int[] dialogViewSize = unDisplayViewSize(LayoutInflater.from(view.getContext()).inflate(layoutId, null));
        int dialogViewWidth = dialogViewSize[0];
        int dialogViewHeight = dialogViewSize[1];
        //设置view的数据
        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();
        int viewX = (int) view.getX();
        int viewY = (int) view.getY();

        // 计算 dialogView 的横坐标
        switch (horizontalPosition) {
            case LEFT:
                if (width != 0) {
                    dialogViewX = viewX - width;
                } else {
                    dialogViewX = viewX - (dialogViewWidth + horizontalOffset);
                }
                break;
            case ALIGN_RIGHT:
                if (width != 0) {
                    dialogViewX = viewX + viewWidth - width + horizontalOffset;
                } else {
                    dialogViewX = viewX + viewWidth - (dialogViewWidth + horizontalOffset);
                }
                break;
            case CENTER:
                if (width != 0) {
                    dialogViewX = viewX - width;
                } else {
                    dialogViewX = viewX - (dialogViewWidth + horizontalOffset);
                }
                break;
            case ALIGN_LEFT:
                if (width != 0) {
                    dialogViewX = viewX - width;
                } else {
                    dialogViewX = viewX - (dialogViewWidth + horizontalOffset);
                }
                break;
            case RIGHT:
                if (width != 0) {
                    dialogViewX = viewX - width;
                } else {
                    dialogViewX = viewX - (dialogViewWidth + horizontalOffset);
                }
                break;
        }

        // 计算 dialogView 的横坐标
        switch (verticalPosition) {
            case ABOVE:
                if (height != 0) {
                    dialogViewY = viewY - height;
                } else {
                    dialogViewY = viewY - (dialogViewHeight + verticalOffset);
                }
                break;
            case ALIGN_BOTTOM:
                if (height != 0) {
                    dialogViewY = viewY + viewHeight - height + verticalOffset;
                } else {
                    dialogViewY = viewY + viewHeight - (dialogViewHeight + verticalOffset);
                }
                break;
            case CENTER:
                if (height != 0) {
                    dialogViewY = viewY - height;
                } else {
                    dialogViewY = viewY - (dialogViewHeight + verticalOffset);
                }
                break;
            case ALIGN_TOP:
                if (height != 0) {
                    dialogViewY = viewX - height;
                } else {
                    dialogViewY = viewX - (dialogViewHeight + verticalOffset);
                }
                break;
            case BELOW:
                if (height != 0) {
                    dialogViewY = viewX - height;
                } else {
                    dialogViewY = viewX - (dialogViewHeight + verticalOffset);
                }
                break;
        }

    }

    /**
     * 在 view 显示之前读取宽高
     */
    private int[] unDisplayViewSize(View view) {
        int[] size = new int[2];
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        size[0] = view.getMeasuredWidth();
        size[1] = view.getMeasuredHeight();
        return size;
    }
}
