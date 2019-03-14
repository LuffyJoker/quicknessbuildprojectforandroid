package com.peng.commonlib.ui.demo;

import com.blankj.utilcode.util.LogUtils;

import com.blankj.utilcode.util.Utils;
import com.peng.commonlib.R;
import com.peng.commonlib.data.constant.RespCode;
import com.peng.commonlib.data.network.entity.DemoEntity;
import com.peng.commonlib.data.network.entity.Resp;
import com.peng.commonlib.ui.base.presenter.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 * presenter 层使用方式示例
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
                .flatMap(new Function<Resp<DemoEntity>, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Resp<DemoEntity> demoEntityResp) throws Exception {
                        if (!(demoEntityResp instanceof Resp<?>)) {
                            throw new IllegalStateException(Utils.getApp().getString(R.string.invalid_response));
                        }
                        if (demoEntityResp.code == RespCode.SUCCESS) {

                        } else {

                        }
                        return null;
                    }
                });
//        flatMap {
//            if (it !is Resp<*>) {
//                throw IllegalStateException(getString(R.string.invalid_response))
//            }
//            when (it.code) {
//                RespCode.SUCCESS -> mapper.invoke(it)
//            else -> Single.just<T>(it)
//            }
//        }
//                .enqueue(new Callback<Resp<DemoEntity>>() {
//                    @Override
//                    public void onResponse(Call<Resp<DemoEntity>> call, Response<Resp<DemoEntity>> response) {
//                        //数据请求成功
//                        LogUtils.d(response.body().tdata.getStoreName());
//                    }
//
//                    @Override
//                    public void onFailure(Call<Resp<DemoEntity>> call, Throwable t) {
//                        //数据请求失败
//                        LogUtils.d(t);
//                    }
//                });
    }
}
