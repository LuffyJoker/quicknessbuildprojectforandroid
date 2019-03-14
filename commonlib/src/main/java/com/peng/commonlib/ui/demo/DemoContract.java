package com.peng.commonlib.ui.demo;

import com.peng.commonlib.mvp.interactor.MVPInteractor;
import com.peng.commonlib.mvp.presenter.MVPPresenter;

import com.peng.commonlib.data.network.entity.DemoEntity;
import com.peng.commonlib.data.network.entity.Resp;
import com.peng.commonlib.ui.base.view.MVPOnDistributeActionTerminalProgress;

import io.reactivex.Completable;
import retrofit2.Call;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 * 聚合 View、Presenter、Interactor 进行统一管理
 */
public interface DemoContract {

    /**
     * View 层，用于在 presenter 层回调 View 的相关方法
     */
    interface View extends MVPOnDistributeActionTerminalProgress {
        void success();

        void fail();
    }

    /**
     * 是 V 层和 M 层枢纽，presenter 主要用户和 M、V 之间的交互
     * @param <V>
     * @param <I>
     */
    interface Presenter<V extends View, I extends Interactor> extends MVPPresenter<V, I> {
        /**
         * 通过 guid 查询备注
         */
        Completable queryUserByUserID(Long id, int age);

        /**
         * 获取设备绑定状态
         */
        void fetchBindingState();
    }

    /**
     * 交互器，主要用户操作各项数据，交由 presenter 层处理。这一层被称为 Model 层
     */
    interface Interactor extends MVPInteractor {
        /**
         * 根据本地 id 获取用户信息（数据库查询示例）
         */
        Completable queryUserByUserID(Long id, int age);


        /**
         * 查询设备绑定状态（网络请求示例）
         */
        Call<Resp<DemoEntity>> fetchBindingState();
    }
}
