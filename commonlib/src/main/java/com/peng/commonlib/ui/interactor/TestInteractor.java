package com.peng.commonlib.ui.interactor;

import com.peng.commonlib.database.repository.UserRepository;
import com.peng.commonlib.mvp.interactor.BaseInteractor;
import com.peng.commonlib.ui.contract.TestContract;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 */
public class TestInteractor extends BaseInteractor implements TestContract.Interactor {

    public UserRepository userRepository;

    @Inject
    public TestInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Completable queryUserByUserID(Long id, int age) {
        return userRepository.queryUserByUserId(123L,123);
    }
}
