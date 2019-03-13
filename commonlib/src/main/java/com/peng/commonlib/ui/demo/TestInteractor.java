package com.peng.commonlib.ui.demo;

import com.peng.commonlib.database.repository.UserRepository;
import com.peng.commonlib.mvp.interactor.BaseInteractor;

import com.peng.commonlib.network.ApiHelper;
import com.peng.commonlib.network.entity.FindDeviceStatusNew;
import com.peng.commonlib.network.entity.Resp;


import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 */
public class TestInteractor extends BaseInteractor implements TestContract.Interactor {

    public UserRepository userRepository;
    private ApiHelper mApiHelper;

    @Inject
    public TestInteractor(UserRepository userRepository,ApiHelper apiHelper) {
        this.userRepository = userRepository;
        mApiHelper = apiHelper;
    }

    @Override
    public Completable queryUserByUserID(Long id, int age) {
        return userRepository.queryUserByUserId(123L,123);
    }

    @Override
    public Call<Resp<FindDeviceStatusNew>> fetchBindingState() {
        return mApiHelper.fetchBindingState("B481EC12F76B7BA663E6D2735E9B45B6");
    }
}
