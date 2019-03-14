package com.peng.commonlib.data.database.repository.userinfo;

import com.peng.commonlib.data.entity.User;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Mr.Q on 2019/2/21.
 * 描述：
 */
public interface IUserRepo {

    Completable insertOrUpdate(String goodsGuid, String remark);

    Completable queryUserByUserId(Long userID, int age);

    Maybe<User> queryRemarkByGoodsGuid(String goodsGuid);
}
