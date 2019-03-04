package com.peng.commonlib.ui.contract;

import com.peng.commonlib.mvp.interactor.MVPInteractor;
import com.peng.commonlib.mvp.presenter.MVPPresenter;
import com.peng.commonlib.mvp.view.MVPView;

import io.reactivex.Completable;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 *      聚合 View、Presenter、Interactor 进行统一管理
 *
 */
public interface TestContract {

    interface View extends MVPView {

    }

    interface Presenter<V extends View, I extends Interactor > extends MVPPresenter<V, I> {
        /**
         * 通过guid查询备注
         */
        Completable queryUserByUserID(Long id, int age);
    }

    interface Interactor extends MVPInteractor{
        /**
         * 根据本地id获取商品
         */
        Completable queryUserByUserID(Long id, int age);
    }
}
