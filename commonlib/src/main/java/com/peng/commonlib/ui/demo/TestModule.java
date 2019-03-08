package com.peng.commonlib.ui.demo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 *      Dagger:提供对象实例层
 */
@Module
public class TestModule {
    @Provides
    TestContract.Interactor provideTestInteractor(TestInteractor testInteractor) {
        return testInteractor;
    }

    @Provides
    TestContract.Presenter<TestContract.View, TestContract.Interactor> provideTestPresenter(TestPresenter<TestContract.View, TestContract.Interactor> testPresenter) {
        return testPresenter;
    }
}
