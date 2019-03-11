package com.peng.commonlib.ui.demo;

import com.blankj.utilcode.util.LogUtils;
import com.peng.commonlib.constant.RespCode;
import com.peng.commonlib.mvp.presenter.BasePresenter;

import com.peng.commonlib.network.entity.FindDeviceStatusNew;
import com.peng.commonlib.network.entity.Resp;
import com.peng.commonlib.rx.ApiException;
import com.peng.commonlib.rx.observer.DistributeProgressSingleObserver;
import com.peng.commonlib.rx.threadswitch.TransformerFactory;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.functions.Function;

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
                .map(new Function<Resp<FindDeviceStatusNew>, FindDeviceStatusNew>() {
                    @Override
                    public FindDeviceStatusNew apply(Resp<FindDeviceStatusNew> findDeviceStatusNewResp) throws Exception {
                        if (findDeviceStatusNewResp.code == RespCode.SUCCESS) {
                            return findDeviceStatusNewResp.data;
                        }
                        throw new ApiException(findDeviceStatusNewResp.code, findDeviceStatusNewResp.msg);
                    }
                })
                .compose(TransformerFactory.ioToMain())
                .subscribe(new DistributeProgressSingleObserver(view, true, true) {
                    @Override
                    public void success(Object response) {
                        LogUtils.d(response);
                    }

                    @Override
                    public void failure(int code, String msg) {
                        LogUtils.d("失败：" + code + "   " + msg);
                    }
                });
//                .subscribe(new BaseObserver<FindDeviceStatusNew>(view) {
//                    @Override
//                    protected void onSuccess(FindDeviceStatusNew findDeviceStatusNew) {
//
//                    }
//
//                    @Override
//                    protected void onFail(String message) {
//
//                    }
//                });

    }
}
