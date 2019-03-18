package com.peng.commonlib.data.preference.rx;

import com.peng.commonlib.data.entity.Question;

import io.reactivex.Observable;

/**
 * create by Mr.Q on 2019/3/18.
 * 类介绍：
 *  作为 data layer 的 SharedPreference 对外暴露的接口，承载了所有需异步化的 haredPreference 操作定义
 */
public interface PreferenceHelper {
    Observable<Question> getQuestion();

    void saveQuestion(Question question);
}
