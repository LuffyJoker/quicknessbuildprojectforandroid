package com.peng.commonlib.rx.observer.observable;

import com.peng.commonlib.ui.base.view.ErrorMessageGeneratorImpl;
import com.peng.commonlib.ui.base.view.ErrorMessageHandlerImpl;
import com.peng.commonlib.ui.base.exception.RespIgnoreException;
import com.peng.commonlib.ui.base.view.MVPTerminalProgress;

/**
 * Created by Mr.Q on 2019/3/14
 * 描述：
 */
public abstract class DistProgObserverAdapter<T> extends DistProgObserver<T>{

    private MVPTerminalProgress mvpTerminalProgress;
    private boolean printStackTrace = true;
    private boolean autoHandleErrorMsg = true;
    private boolean showProgress = true;
    private boolean hideProgress = true;

    private ErrorMessageGeneratorImpl errorMessageGenerator;
    private ErrorMessageHandlerImpl errorMessageHandler;

    public DistProgObserverAdapter(MVPTerminalProgress mvpTerminalProgress, boolean showProgress, boolean hideProgress) {
        super(mvpTerminalProgress, showProgress, hideProgress);
        errorMessageGenerator = new ErrorMessageGeneratorImpl(mvpTerminalProgress);
        errorMessageHandler = new ErrorMessageHandlerImpl(mvpTerminalProgress);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof RespIgnoreException) {
            // Ignore the exception, just to interrupt the observable stream.
        } else {
            if (printStackTrace){
                e.printStackTrace();
            }
            if (autoHandleErrorMsg){
                errorMessageHandler.handleErrorMessage(errorMessageGenerator.generateErrorMessage(e));
            }
        }
    }
}
