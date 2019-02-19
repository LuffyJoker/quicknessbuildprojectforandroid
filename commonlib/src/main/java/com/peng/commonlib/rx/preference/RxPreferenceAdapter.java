package com.peng.commonlib.rx.preference;

import com.f2prateek.rx.preferences2.Preference;

/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 *      AbsPreferenceAdapter 的默认实现，真正对 Preference 的操作委托给了 RxPreference 框架
 */
public class RxPreferenceAdapter<T extends Object> extends PreferenceAdapter<T> {

    private Preference<T> pref;

    public RxPreferenceAdapter(Preference<T> pref) {
        this.pref = pref;
    }

//    public T getValue(Object thisRef, KProperty<?> property) {
//        return pref.get();
//    }
//
//    public void setValue(Object thisRef, KProperty<?> property, T value) {
//        pref.set(value);
//    }
}
