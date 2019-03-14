package com.peng.commonlib.ui.base.presenter;


import com.peng.commonlib.ui.base.interactor.MVPInteractor;
import com.peng.commonlib.ui.base.view.MVPView;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 *      MVP分层架构: 基础 Presenter 接口
 */
public interface MVPPresenter<V extends MVPView, I extends MVPInteractor> {
    /**
     * V : MVPView 已 Attach
     */
    void onAttach(V view);

    /**
     * V : MVPView 已 Detach
     */
    void onDetach();
}
