package com.peng.commonlib.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.peng.commonlib.database.converter.DateConverter;
import com.peng.commonlib.database.dao.QuestionDao;
import com.peng.commonlib.database.dao.UserDao;
import com.peng.commonlib.database.entity.Question;
import com.peng.commonlib.database.entity.User;

/**
 * Created by Mr.Q on 2019/2/21.
 * 描述：
 *      1、@Database 用来注解类，并且注解的类必须是继承自 RoomDatabase 的抽象类。
 *      2、该类主要作用是创建数据库和创建 Daos（data access objects，数据访问对象）
 *      3、每次创建AppDatabase实例都会产生比较大的开销，所以应该将AppDatabase设计成单例的
 *      4、@TypeConverters 为了将 Room 无法存储的类型转换为 Room 可以存储的类型，转换方式为 DateConverter.class
 *      5、数据库升级，Migration 类用于迁移数据库
 */
@Database(
        entities = {
                Question.class,
                User.class,
//                EmployeeUser.class,
//                EnvironmentInfo.class,
//                Enterprise.class,
//                Store.class,
//                Perm.class,
//                Dish.class,
//                DishType.class,
//                DishProperty.class,
//                DishSubgroup.class,
//                DishSku.class,
//                PaymentType.class,
//                GoodsRemarks.class //商品的备注
        },
        version = 1,
        exportSchema = false
)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();
    public abstract UserDao userDao();

//    /**
//     * 获取员工信息实体的Dao操作对象
//     */
//    abstract fun employeeDao(): EmployeeDao

//    abstract fun environmentDao():EnvironmentDao
//
//    abstract fun dishDao():DishDao
//
//    abstract fun paymentTypeDao():PaymentTypeDao
//
//    abstract fun remarkDao():GoodsRemarkDao
}
