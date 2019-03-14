package com.peng.commonlib.ui.demo;

import com.peng.commonlib.data.database.repository.userinfo.UserRepository;
import com.peng.commonlib.ui.base.interactor.BaseInteractor;

import com.peng.commonlib.data.network.ApiHelper;
import com.peng.commonlib.data.network.entity.DemoEntity;
import com.peng.commonlib.data.network.entity.Resp;


import javax.inject.Inject;

import io.reactivex.Completable;
import retrofit2.Call;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 *      在该类中，使用了@Inject 注解，表示将userRepository、mApiHelper 类的实例注入到该类中
 */
public class DemoInteractor extends BaseInteractor implements DemoContract.Interactor {

    public UserRepository userRepository;
    private ApiHelper mApiHelper;

    @Inject
    public DemoInteractor(UserRepository userRepository, ApiHelper apiHelper) {
        this.userRepository = userRepository;
        mApiHelper = apiHelper;
    }

    @Override
    public Completable queryUserByUserID(Long id, int age) {
        return userRepository.queryUserByUserId(123L,123);
    }

    @Override
    public Call<Resp<DemoEntity>> fetchBindingState() {
        return mApiHelper.fetchBindingState("B481EC12F76B7BA663E6D2735E9B45B6");
    }
}
