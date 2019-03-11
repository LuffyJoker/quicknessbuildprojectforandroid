package com.peng.commonlib.mvp.presenter;

import com.peng.commonlib.mvp.interactor.MVPInteractor;
import com.peng.commonlib.mvp.view.MVPView;

import javax.inject.Inject;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 *      基础 Presenter，持有 View 的引用、Interactor 的引用
 */
public abstract class BasePresenter<V extends MVPView, I extends MVPInteractor> implements MVPPresenter<V, I> {

    private I interactor;

    protected V view = null;

    private Boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void onAttach(V view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        view = null;
    }
}
