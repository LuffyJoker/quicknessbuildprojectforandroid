package com.peng.commonlib.ui.module;

import com.peng.commonlib.ui.contract.TestContract;
import com.peng.commonlib.ui.interactor.TestInteractor;
import com.peng.commonlib.ui.presenter.UserPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 */
@Module
public class TestModule {
    @Provides
    TestContract.Interactor provideTestInteractor(TestInteractor testInteractor) {
        return testInteractor;
    }

    @Provides
    TestContract.Presenter<TestContract.View, TestContract.Interactor> provideTestPresenter(UserPresenter<TestContract.View, TestContract.Interactor> testPresenter) {
        return testPresenter;
    }
}
