package com.peng.commonlib.rx.preference;

/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 *      1、继承自 ReadWriteProperty 以实现属性委托；
 *      2、预定义 value 以封装委托属性：优点是使用方便，缺点是占用主线程
 *      3、预定义 stream 以支持到响应式的适配：缺点是使用繁琐，优点是可在后台线程操作
 */
abstract class PreferenceAdapter<T extends Object>
//        implements ReadWriteProperty<Object, T>
{

}
