package com.peng.commonlib.rx.transformer;

/**
 * create by Mr.Q on 2019/3/5.
 * 类介绍：
 * 线程切换工厂，对所有的线程切换进行统一管理
 */
public class TransformerFactory {

    public static <T> ComputationMainTransformer<T> computationToMain() {
        return new ComputationMainTransformer();
    }

    public static <T> IoMainTransformer<T> ioToMain() {
        return new IoMainTransformer();
    }

    public static <T> NewThreadMainTransformer<T> newThreadToMain() {
        return new NewThreadMainTransformer();
    }

    public static <T> SingleMainTransformer<T> singleToMain() {
        return new SingleMainTransformer();
    }

    public static <T> TrampolineMainTransformer<T> trampolineToMain() {
        return new TrampolineMainTransformer();
    }

}
