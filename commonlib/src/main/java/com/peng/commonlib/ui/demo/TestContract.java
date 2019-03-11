package com.peng.commonlib.ui.demo;

import com.peng.commonlib.mvp.interactor.MVPInteractor;
import com.peng.commonlib.mvp.presenter.MVPPresenter;

import com.peng.commonlib.network.entity.FindDeviceStatusNew;
import com.peng.commonlib.network.entity.Resp;
import com.peng.commonlib.ui.base.view.MVPOnDistributeActionTerminalProgress;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 * 聚合 View、Presenter、Interactor 进行统一管理
 */
public interface TestContract {

    interface View extends MVPOnDistributeActionTerminalProgress {
        void success();

        void fail();

    }

    interface Presenter<V extends View, I extends Interactor> extends MVPPresenter<V, I> {
        /**
         * 通过guid查询备注
         */
        Completable queryUserByUserID(Long id, int age);

        /**
         * 获取设备绑定状态
         */
        void fetchBindingState();
    }

    interface Interactor extends MVPInteractor {
        /**
         * 根据本地id获取商品
         */
        Completable queryUserByUserID(Long id, int age);


        /**
         * 查询设备绑定状态
         */
        Single<Resp<FindDeviceStatusNew>> fetchBindingState();


    }
}
