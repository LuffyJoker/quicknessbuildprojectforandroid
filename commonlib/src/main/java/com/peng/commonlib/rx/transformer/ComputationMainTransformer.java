package com.peng.commonlib.rx.transformer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 *      密集计算线程切换到主线程
 */
public class ComputationMainTransformer<T> extends BaseTransformer<T> {
    public ComputationMainTransformer() {
        super(Schedulers.computation(), AndroidSchedulers.mainThread());
    }
}
