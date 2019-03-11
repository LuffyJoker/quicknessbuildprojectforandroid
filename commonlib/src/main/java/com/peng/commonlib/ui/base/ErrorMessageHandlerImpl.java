package com.peng.commonlib.ui.base;

/**
 * create by Mr.Q on 2019/3/11.
 * 类介绍：
 */
public class ErrorMessageHandlerImpl<T extends OnErrorAction> implements OnErrorMessageHandler{

    private T mOnErrorAction;

    public ErrorMessageHandlerImpl(T onErrorAction){
        mOnErrorAction = onErrorAction;
    }

    @Override
    public void handleErrorMessage(CharSequence message) {
        mOnErrorAction.showErrorMsg(message);
    }
}
