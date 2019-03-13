package com.peng.commonlib.ui.demo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 *      Dagger:提供对象实例层
 *      1、使用依赖注入的方式提供 presenter、interactor 实例
 */
@Module
public class DemoModule {
    @Provides
    DemoContract.Interactor provideTestInteractor(DemoInteractor demoInteractor) {
        return demoInteractor;
    }

    @Provides
    DemoContract.Presenter<DemoContract.View, DemoContract.Interactor> provideTestPresenter(DemoPresenter<DemoContract.View, DemoContract.Interactor> demoPresenter) {
        return demoPresenter;
    }
}
