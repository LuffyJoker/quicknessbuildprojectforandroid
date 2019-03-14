package com.peng.commonlib.rx.observer.single;


import com.blankj.utilcode.util.LogUtils;
import com.peng.commonlib.data.constant.RespCode;

import com.peng.commonlib.data.network.entity.Resp;
import com.peng.commonlib.ui.base.view.MVPOnDistributeActionTerminalProgress;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 */
public abstract class DistributeProgressSingleObserver<T> extends ProgressSingleObserver<T> {

    private MVPOnDistributeActionTerminalProgress mMvpTerminalProgress;
    private boolean mShowProgress = true;
    private boolean mHideProgress = true;

    public DistributeProgressSingleObserver(MVPOnDistributeActionTerminalProgress mvpTerminalProgress) {
        super(mvpTerminalProgress);
        mMvpTerminalProgress = mMvpTerminalProgress;
    }

    @Override
    public void onSuccess(T t) {
        super.onSuccess(t);
        Resp<?> resp = (Resp<?>) t;
        if (resp.code == RespCode.SUCCESS) {
            success(t);
        } else if (resp.code == RespCode.UNAUTHORIZED) {
            mMvpTerminalProgress.showUnauthorized(resp.msg);
        } else if (resp.code == RespCode.INVALID_ACCOUNT_RECORD) {
            mMvpTerminalProgress.showInvalidAccountRecord();
        } else {
            failure(resp.code, resp.msg);
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        LogUtils.d("出错");
    }

    public abstract void success(T response);

    public abstract void failure(int code, String msg);
}


