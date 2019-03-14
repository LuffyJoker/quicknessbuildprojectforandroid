package com.peng.commonlib.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.peng.commonlib.mvp.view.MVPView;

/**
 * Created by Mr.Q on 2019/2/19.
 * 描述：
 *
 * MVP抽象类，继承自Dagger抽象类，扩展以支持MVP分层架构
 *
 * feature: 布局id模板方法
 *
 * feature: parentActivity持有
 *
 * feature: ARouter注入
 *
 * feature: 键盘事件处理
 *
 * feature: initView模板方法
 *
 * feature: initData模板方法
 *
 * feature: Dagger2依赖注入支持，且支持MVP，故需要在FragmentModule中进行声明，如下：
 *      @ContributesAndroidInjector(modules = [XXXFragmentModule::class])
 *      abstract fun contributeXXXFragment(): XXXFragment
 */
abstract class AbsMVPFragment extends AbsDaggerFragment implements MVPView {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 设置状态栏的状态
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        /**
         *  在该生命周期执行View向Presenter的attach操作，原因如下：
         *     一旦该方法执行，那么 Presenter 就能拿到 View 的引用，就有可能访问 View 的方法执行 UI 操作，而此时如果视图尚未创建成功，会发生异常。故放在onViewCreated之后执行
         */
        attachPresenter();
    }

    @Override
    public void onDestroyView() {

        /**
         * 在该生命周期执行 View 向 Presenter 的 detach 操作，原因如下：
         * 如果不在此执行，那么如果视图已经销毁，但 Presenter 根据 View 的引用访问 View 的方法执行 UI 操作，就会发生异常，故放在 onDestroyView 之前执行
         */
        detachPresenter();

        super.onDestroyView();

    }

    /**
     * 分离 Presenter
     *
     * @author pq
     * create at 2019/2/19
     */
    abstract void detachPresenter();

    /**
     * 接入 presenter
     *
     * @author pq
     * create at 2019/2/19
     */
    abstract void attachPresenter();
}
