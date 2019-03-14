package com.peng.commonlib.rx.transformer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 *  trampoline,在当前线程立即执行任务，如果当前线程有任务在执行，则会将其暂停，等插入进来的任务执行完之后，再将未完成的任务接着执行。类似中断现象
 */
public class TrampolineMainTransformer<T> extends BaseTransformer<T> {
    public TrampolineMainTransformer() {
        super(Schedulers.trampoline(), AndroidSchedulers.mainThread());
    }
}
