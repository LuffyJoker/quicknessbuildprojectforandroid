package com.peng.commonlib.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.peng.commonlib.database.entity.Question;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Mr.Q on 2019/2/21.
 * 描述：
 *      1、@Dao 用来注解一个接口或者抽象方法
 *      2、该类的作用是提供访问数据库的方法
 *
 */
@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Question> question);

    @Query("SELECT * FROM questions")
    Flowable<List<Question>> loadAll();

}
