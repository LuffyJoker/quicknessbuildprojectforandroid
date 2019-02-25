package widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.peng.commonlib.R;

/**
 * Created by Mr.Q on 2019/2/23.
 * 描述：
 */
public class LoadingLayout extends FrameLayout {

    private int emptyViewLayoutId, errorViewLayoutId, loadingViewLayoutId;
    private String titleDes,contentDes;
    private View emptyView, errorView, loadingView;
    private OnClickListener onLoadingClickListener;
    private Animation rightCircle;    //向右旋转的360度的动画

    private Button btnNoData;
    private TextView tvTitle,tvDes;


    public LoadingLayout(Context context) {
        this(context, null);
    }


    public LoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LoadingLayout, 0, 0);
        try {
            emptyViewLayoutId = a.getResourceId(R.styleable.LoadingLayout_emptyView, R.layout.include_empty_layout); //空数据页面
            errorViewLayoutId = a.getResourceId(R.styleable.LoadingLayout_errorView, R.layout.include_net_error_layout);//网络失败页面
            loadingViewLayoutId = a.getResourceId(R.styleable.LoadingLayout_loadingView, R.layout.include_load_layout);//加载页面
            titleDes = a.getString(R.styleable.LoadingLayout_titleDes);// 错误描述的标题
            contentDes = a.getString(R.styleable.LoadingLayout_contentDes);// 错误描述的内容
            loadingViewLayoutId = a.getResourceId(R.styleable.LoadingLayout_loadingView, R.layout.include_load_layout);//加载页面
            LayoutInflater inflater = LayoutInflater.from(getContext());
            emptyView = inflater.inflate(emptyViewLayoutId, this, true);
            errorView = inflater.inflate(errorViewLayoutId, this, true);
            loadingView = inflater.inflate(loadingViewLayoutId, this, true);
            initViews();

        } finally {
            a.recycle();
        }

    }

    //初始化视图
    private void initViews() {
        rightCircle = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rightCircle.setDuration(500);
        rightCircle.setFillAfter(true);

        btnNoData = findViewById(R.id.btn_retry);

        tvTitle = findViewById(R.id.tv_emptyTitle);
        tvTitle.setText(titleDes);
        tvDes = findViewById(R.id.tv_emptyDes);
        tvDes.setText(contentDes);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        for (int i = 0; i < getChildCount() - 1; i++) {
            getChildAt(i).setVisibility(GONE);
        }

        btnNoData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLoadingClickListener != null) {
                    onLoadingClickListener.onClick(v);
                }
            }
        });
    }

    public void setOnRetryClickListener(OnClickListener onLoadingClickListener) {
        this.onLoadingClickListener = onLoadingClickListener;
    }

    //显示空数据页面
    public void showEmpty() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 0) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    //显示网络失败页面
    public void showError() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 1) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    //显示加载Loading页面
    public void showLoading() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 2) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    //显示想要加载的内容
    public void showContent() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 3) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }
}

