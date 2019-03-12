package com.peng.commonlib.database.repository.environment;

import io.reactivex.Completable;

/**
 * create by Mr.Q on 2019/3/12.
 * 类介绍：
 */
public interface EnvironmentRepo {
    /**
     * 插入业务环境数据
     */
//    Completable insert(EnvironmentInfo environmentInfo);

    /**
     * 加载业务环境数据
     */
//    Maybe<EnvironmentInfo> load();

    /**
     * 更新token
     */
    Completable updateToken(String token);

    /**
     * 更新用户
     */
    Completable updateUserInfo(String userGuid, String userName, String userAccount, String userTel);

    /**
     * 保存token，插入一条数据
     */
    Completable saveToken(String token);

    /**
     * 更新除token外的列
     */
//    Completable updateAllExceptToken( Verification verification);
}
