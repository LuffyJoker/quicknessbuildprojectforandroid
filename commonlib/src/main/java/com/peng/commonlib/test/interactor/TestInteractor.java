package com.peng.commonlib.test.interactor;

import com.peng.commonlib.database.repository.UserRepository;
import com.peng.commonlib.mvp.interactor.BaseInteractor;
import com.peng.commonlib.test.contract.TestContract;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 */
public class TestInteractor extends BaseInteractor implements TestContract.Interactor {

    private UserRepository userRepository;

    @Inject
    public TestInteractor(
//            ApiHelper apiHelper,
//            PreferenceHelper preferenceHelper,
            UserRepository userRepository) {
    }

    @Override
    public Completable queryUserByUserID(String id, int age) {
        return userRepository.queryUserByUserId("123",123);
    }
}
