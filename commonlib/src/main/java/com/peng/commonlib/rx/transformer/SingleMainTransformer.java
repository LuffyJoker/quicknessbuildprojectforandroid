package com.peng.commonlib.rx.transformer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 *      Single，切换到主线程
 */
public class SingleMainTransformer<T> extends BaseTransformer<T> {
    public SingleMainTransformer() {
        super(Schedulers.single(), AndroidSchedulers.mainThread());
    }
}
