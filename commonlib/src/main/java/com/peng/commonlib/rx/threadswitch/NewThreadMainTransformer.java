package com.peng.commonlib.rx.threadswitch;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 *      新开的一个线程，切换到主线程
 */
public class NewThreadMainTransformer<T> extends BaseTransformer<T> {
    protected NewThreadMainTransformer() {
        super(Schedulers.newThread(), AndroidSchedulers.mainThread());
    }
}
