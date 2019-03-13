package com.peng.commonlib.ui.demo;

import com.blankj.utilcode.util.LogUtils;
import com.peng.commonlib.mvp.presenter.BasePresenter;
import com.peng.commonlib.network.entity.FindDeviceStatusNew;
import com.peng.commonlib.network.entity.Resp;

import javax.inject.Inject;

import io.reactivex.Completable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 */
public class TestPresenter<V extends TestContract.View, I extends TestContract.Interactor> extends BasePresenter<V, I> implements TestContract.Presenter<V, I> {

    public I interactor;

    @Inject
    public TestPresenter(I interactor) {
        this.interactor = interactor;
    }

    @Override
    public Completable queryUserByUserID(Long id, int age) {
        return interactor.queryUserByUserID(id, age);
    }

    @Override
    public void fetchBindingState() {
        interactor.fetchBindingState()
                .enqueue(new Callback<Resp<FindDeviceStatusNew>>() {
                    @Override
                    public void onResponse(Call<Resp<FindDeviceStatusNew>> call, Response<Resp<FindDeviceStatusNew>> response) {
                        //数据请求成功
                        LogUtils.d(response.body().data);
                    }

                    @Override
                    public void onFailure(Call<Resp<FindDeviceStatusNew>> call, Throwable t) {
                        //数据请求失败
                        LogUtils.d(t);
                    }
                });
    }
}
