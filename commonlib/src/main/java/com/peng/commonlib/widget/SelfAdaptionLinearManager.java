package com.peng.commonlib.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mr.Q on 2019/2/24.
 * 描述：
 *  根据内容自适应高度的 RecyclerView 线性管理器
 */
public class SelfAdaptionLinearManager extends LinearLayoutManager {

    private int[] mMeasuredDimension = new int[2];

    public SelfAdaptionLinearManager(Context context) {
        super(context);
    }

    public SelfAdaptionLinearManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public SelfAdaptionLinearManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onMeasure(@NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state, int widthSpec, int heightSpec) {
        int widthMode = View.MeasureSpec.getMode(widthSpec);
        int heightMode = View.MeasureSpec.getMode(heightSpec);
        int widthSize = View.MeasureSpec.getSize(widthSpec);
        int heightSize = View.MeasureSpec.getSize(heightSpec);
        int width = 0;
        int height = 0;

        for (int i = 0; i <= getItemCount(); i++) {
            try {
                measureScrapChild(recycler, i,
                        widthSpec,
                        View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                        mMeasuredDimension);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            if (getOrientation() == LinearLayoutManager.HORIZONTAL) {
                width += mMeasuredDimension[0];
                if (i == 0) {
                    height = mMeasuredDimension[1];
                }
            } else {
                height += mMeasuredDimension[1];
                if (i == 0) {
                    width = mMeasuredDimension[0];
                }
            }
        }

        // 宽度模式还未处理，以后拓展
        switch (widthMode) {

        }

        switch (heightMode) {
            case View.MeasureSpec.EXACTLY:
                height = heightSize;
                break;
        }
        setMeasuredDimension(widthSpec, height);
    }

    /**
     * 设置每一个item的布局参数
     *
     * @author pq
     * create at 2019/2/24
     */
    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec, int heightSpec, int[] measuredDimension) {
        View view = recycler.getViewForPosition(position);
        if (view != null) {
            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
            int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec, getPaddingTop() + getPaddingBottom(), p.height);
            view.measure(widthSpec, childHeightSpec);
            measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
            measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
            recycler.recycleView(view);
        }
    }
}
