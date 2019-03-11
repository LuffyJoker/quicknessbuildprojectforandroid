package com.peng.commonlib.ui.base.dialog.option;

import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * create by Mr.Q on 2019/3/4.
 * 类介绍：
 *      View 支持类，用于查找控件，给控件设置值以及相关属性
 */
public class ViewHolder {

    private View rootView;
    private SparseArray<View> views;

    public ViewHolder(View rootView, SparseArray<View> views) {
        this.rootView = rootView;
        this.views = views;
    }

    /**
     * 找到 View
     * @param viewId
     * @param <T>
     * @return
     */
    private <T> T getView(int viewId) {
        if (views.get(viewId) != null) {
            return (T) views.get(viewId);
        }
        if (rootView.findViewById(viewId) != null) {
            views.put(viewId, rootView.findViewById(viewId));
            return (T) rootView.findViewById(viewId);
        }
        return null;
    }

    /**
     * 给 TextView 设置文本
     * @param viewId
     * @param text
     * @return
     */
    private ViewHolder setText(int viewId, CharSequence text) {
        TextView textView = getView(viewId);
        if (textView != null) {
            textView.setText(text);
        }
        return this;
    }

    private ViewHolder setText(int viewId, int resID) {
        TextView textView = getView(viewId);
        if (textView != null) {
            textView.setText(resID);
        }
        return this;
    }

    /**
     * 设置 TextView 的可见性
     *
     * @param viewId
     * @param visibility View.VISIBLE、View.GONE、View.INVISIBLE
     * @return
     */
    private ViewHolder setVisible(int viewId, int visibility) {
        TextView textView = getView(viewId);
        if (textView != null) {
            textView.setVisibility(visibility);
        }
        return this;
    }

    /**
     * 设置 TextView 的字体颜色
     *
     * @param viewId
     * @param colorID
     * @return
     */
    private ViewHolder setTextColor(int viewId, int colorID) {
        TextView textView = getView(viewId);
        if (textView != null) {
            textView.setTextColor(colorID);
        }
        return this;
    }

    /**
     * 设置 ImageView 的图像
     * @param viewId
     * @param imgRes
     * @return
     */
    private ViewHolder setImageResource(int viewId, int imgRes) {
        ImageView imageView = getView(viewId);
        if (imageView != null) {
            imageView.setImageResource(imgRes);
        }
        return this;
    }

    /**
     * 设置 CheckBox 的选中状态
     * @param viewId
     * @param isChecked
     * @return
     */
    private ViewHolder setChecked(int viewId, boolean isChecked) {
        CheckBox checkBox = getView(viewId);
        if (checkBox != null) {
            checkBox.setChecked(isChecked);
        }
        return this;
    }

    /**
     * 设置 View 的背景图
     * @param viewId
     * @param resId
     * @return
     */
    private ViewHolder setBackgroundResource(int viewId, int resId) {
        View view = getView(viewId);
        if (view != null) {
            view.setBackgroundResource(resId);
        }
        return this;
    }

    /**
     * 设置 View 的背景颜色
     * @param viewId
     * @param colorId
     * @return
     */
    private ViewHolder setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        if (view != null) {
            view.setBackgroundColor(colorId);
        }
        return this;
    }


    /**
     * 设置 View 的点击事件
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 设置 View 的长安事件
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnLongClickListener(listener);
        }
        return this;
    }

}
