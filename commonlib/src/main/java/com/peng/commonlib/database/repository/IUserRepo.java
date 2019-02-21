package com.peng.commonlib.database.repository;

import com.peng.commonlib.database.entity.User;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Mr.Q on 2019/2/21.
 * 描述：
 */
public interface IUserRepo {

    Completable insertOrUpdate(String goodsGuid, String remark);

    Completable queryUserByUserId(String userID, int age);

    Maybe<User> queryRemarkByGoodsGuid(String goodsGuid);
}
