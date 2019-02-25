package widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * Created by Mr.Q on 2019/2/24.
 * 描述：
 * 可以进行旋转的 TextView
 */

public class RotateTextView extends android.support.v7.widget.AppCompatTextView {


    public RotateTextView(Context context) {
        super(context);
    }

    public RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RotateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //倾斜度45,上下左右居中
        canvas.rotate(90f, (getMeasuredWidth() / 2) * 1.0f, (getMeasuredHeight() / 2) * 1.0f);
        super.onDraw(canvas);
    }
}
