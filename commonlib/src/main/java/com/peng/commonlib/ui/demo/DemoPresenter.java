package com.peng.commonlib.ui.demo;

import com.blankj.utilcode.util.Utils;
import com.peng.commonlib.R;
import com.peng.commonlib.data.network.entity.DemoEntity;
import com.peng.commonlib.data.network.entity.Resp;
import com.peng.commonlib.rx.observer.single.DistributeProgressSingleObserverAdapter;
import com.peng.commonlib.rx.transformer.TransformerFactory;
import com.peng.commonlib.ui.base.presenter.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Created by Mr.Q on 2019/2/22.
 * 描述：
 * presenter 层使用方式示例
 */
public class DemoPresenter<V extends DemoContract.View, I extends DemoContract.Interactor> extends BasePresenter<V, I> implements DemoContract.Presenter<V, I> {

    public I interactor;

    @Inject
    public DemoPresenter(I interactor) {
        this.interactor = interactor;
    }

    @Override
    public Completable queryUserByUserID(Long id, int age) {
        return interactor.queryUserByUserID(id, age);
    }

    @Override
    public void fetchBindingState() {
        interactor.fetchBindingState()
                .flatMap(new Function<Resp<DemoEntity>, SingleSource<Resp<DemoEntity>>>() {
                    @Override
                    public SingleSource<Resp<DemoEntity>> apply(Resp<DemoEntity> demoEntityResp) throws Exception {
                        if (!(demoEntityResp instanceof Resp<?>)) {
                            throw new IllegalStateException(Utils.getApp().getString(R.string.invalid_response));
                        } else {
                            return Single.just(demoEntityResp);
                        }
                    }
                })
                .compose(TransformerFactory.ioToMain())
                .subscribe(new DistributeProgressSingleObserverAdapter<Resp<DemoEntity>>(view) {

                    @Override
                    public void success(Resp<DemoEntity> response) {
                        view.success(response);
                    }

                    @Override
                    public void failure(int code, String msg) {
                        view.fail(code,msg);
                    }
                });
    }
}
