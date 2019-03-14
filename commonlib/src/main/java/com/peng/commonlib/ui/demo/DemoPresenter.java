package com.peng.commonlib.ui.demo;

import com.blankj.utilcode.util.LogUtils;
import com.peng.commonlib.mvp.presenter.BasePresenter;
import com.peng.commonlib.data.network.entity.DemoEntity;
import com.peng.commonlib.data.network.entity.Resp;

import javax.inject.Inject;

import io.reactivex.Completable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 *      presenter 层使用方式示例
 */
public class DemoPresenter<V extends DemoContract.View, I extends DemoContract.Interactor> extends BasePresenter<V, I> implements DemoContract.Presenter<V, I> {

    public I interactor;

    @Inject
    public DemoPresenter(I interactor) {
        this.interactor = interactor;
    }

    @Override
    public Completable queryUserByUserID(Long id, int age) {
        return interactor.queryUserByUserID(id, age);
    }

    @Override
    public void fetchBindingState() {
        interactor.fetchBindingState()
                .enqueue(new Callback<Resp<DemoEntity>>() {
                    @Override
                    public void onResponse(Call<Resp<DemoEntity>> call, Response<Resp<DemoEntity>> response) {
                        //数据请求成功
                        LogUtils.d(response.body().tdata.getStoreName());
                    }

                    @Override
                    public void onFailure(Call<Resp<DemoEntity>> call, Throwable t) {
                        //数据请求失败
                        LogUtils.d(t);
                    }
                });
    }
}
