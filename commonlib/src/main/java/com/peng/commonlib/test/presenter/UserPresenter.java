package com.peng.commonlib.test.presenter;

import com.peng.commonlib.mvp.presenter.BasePresenter;
import com.peng.commonlib.test.contract.TestContract;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 */
public class UserPresenter<V extends TestContract.View, I extends TestContract.Interactor> extends BasePresenter<V,I> implements TestContract.Presenter<V,I>{

    private I interactor;

    @Inject
    public UserPresenter(I interactor) {
//        super(interactor);
        this.interactor = interactor;
    }

    @Override
    public Completable queryUserByUserID(String id, int age) {
        return interactor.queryUserByUserID(id,age);
    }
}
