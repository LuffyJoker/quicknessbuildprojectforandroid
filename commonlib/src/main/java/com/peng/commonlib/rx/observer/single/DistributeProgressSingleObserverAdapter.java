package com.peng.commonlib.rx.observer.single;

import com.peng.commonlib.ui.base.ErrorMessageGeneratorImpl;
import com.peng.commonlib.ui.base.ErrorMessageHandlerImpl;
import com.peng.commonlib.ui.base.exception.RespIgnoreException;
import com.peng.commonlib.ui.base.view.MVPOnDistributeActionTerminalProgress;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 */
public abstract class DistributeProgressSingleObserverAdapter<T> extends DistributeProgressSingleObserver<T> {

    private MVPOnDistributeActionTerminalProgress mvpTerminalProgress;
    private boolean printStackTrace = true;
    private boolean autoHandleErrorMsg = true;
    boolean showProgress = true;
    boolean hideProgress = true;
    private ErrorMessageGeneratorImpl errorMessageGenerator;
    private ErrorMessageHandlerImpl errorMessageHandler;

    public DistributeProgressSingleObserverAdapter(MVPOnDistributeActionTerminalProgress mvpTerminalProgress, boolean showProgress, boolean hideProgress) {
        super(mvpTerminalProgress, showProgress, hideProgress);
        errorMessageGenerator = new ErrorMessageGeneratorImpl(mvpTerminalProgress);
        errorMessageHandler = new ErrorMessageHandlerImpl(mvpTerminalProgress);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (e instanceof RespIgnoreException) {
            // Ignore the exception, just to interrupt the observable stream.
        } else {
            if (printStackTrace) {
                e.printStackTrace();
            }
            if (autoHandleErrorMsg) {
                errorMessageHandler.handleErrorMessage(errorMessageGenerator.generateErrorMessage(e));
            }
        }
    }
}
