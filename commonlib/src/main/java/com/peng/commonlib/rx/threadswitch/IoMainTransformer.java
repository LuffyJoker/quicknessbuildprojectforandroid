package com.peng.commonlib.rx.threadswitch;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 *      IO 线程，切换到主线程
 */
public class IoMainTransformer<T> extends BaseTransformer<T> {
    protected IoMainTransformer() {
        super(Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
