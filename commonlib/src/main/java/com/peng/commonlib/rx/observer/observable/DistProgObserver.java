package com.peng.commonlib.rx.observer.observable;

import com.blankj.utilcode.util.Utils;
import com.peng.commonlib.BuildConfig;
import com.peng.commonlib.R;
import com.peng.commonlib.data.constant.RespCode;
import com.peng.commonlib.data.network.entity.Resp;
import com.peng.commonlib.ui.base.view.MVPTerminalProgress;

/**
 * Created by Mr.Q on 2019/3/14
 * 描述：
 */
public abstract class DistProgObserver<T> extends ProgressObserver<T> {
    private MVPTerminalProgress mvpTerminalProgress;
    private boolean showProgress = true;
    private boolean hideProgress = true;


    public DistProgObserver(MVPTerminalProgress mvpTerminalProgress, boolean showProgress, boolean hideProgress) {
        super(mvpTerminalProgress, showProgress, hideProgress);
        this.mvpTerminalProgress = mvpTerminalProgress;
        this.showProgress = showProgress;
        this.hideProgress = hideProgress;
    }

    public DistProgObserver(MVPTerminalProgress mvpTerminalProgress) {
        super(mvpTerminalProgress, true, true);
        this.mvpTerminalProgress = mvpTerminalProgress;
    }

    @Override
    public void onNext(T t) {
        if (!(t instanceof Resp<?>)) {
            if (BuildConfig.DEBUG) {
                throw new IllegalStateException(Utils.getApp().getString(R.string.invalid_response));
            } else {
                return;
            }
        }
        Resp<?> resp = (Resp<?>) t;
        if (resp.code == RespCode.SUCCESS) {
            success(t);
        } else if (resp.code == RespCode.UNAUTHORIZED) {
            if (mvpTerminalProgress != null) {
                mvpTerminalProgress.showUnauthorized(resp.msg);
            }
        } else if (resp.code == RespCode.INVALID_ACCOUNT_RECORD) {
            if (mvpTerminalProgress != null) {
                mvpTerminalProgress.showInvalidAccountRecord();
            }
        } else {
            failure(resp.code, resp.msg);
        }
    }

    abstract void success(T response);

    abstract void failure(int code, String msg);
}
