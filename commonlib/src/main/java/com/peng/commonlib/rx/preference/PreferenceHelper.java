package com.peng.commonlib.rx.preference;

import com.peng.commonlib.data.entity.Question;

import io.reactivex.Observable;

/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 *  作为数据层的 SharedPreference 对外暴露的接口，承载了所有需异步化的 SharedPreference 操作定义
 */
public interface PreferenceHelper {

    Observable<Question> getQuestion();

    void saveQuestion(Question question);

}
