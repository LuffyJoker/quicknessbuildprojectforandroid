package com.peng.commonlib.data.database.repository.userinfo;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.peng.commonlib.data.entity.User;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.functions.Action;

/**
 * Created by Mr.Q on 2019/2/21.
 * 描述：
 *      查询数据的仓库
 */
public class UserRepository implements IUserRepo {


    public UserDao userDao;

    @Inject
    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Completable insertOrUpdate(String userID, String remark) {
        return null;
    }

    @Override
    public Completable queryUserByUserId(final Long userID, final int age) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() {
                User user = userDao.queryUserByUserId(userID);
                if (ObjectUtils.isEmpty(user)) {
                    User bean = new User(userID, age);
                    userDao.insertUsers(bean);
                } else {
                    ToastUtils.showShort(String.valueOf(user.id + user.age));
                }
            }
        });
    }

    @Override
    public Maybe<User> queryRemarkByGoodsGuid(String goodsGuid) {
        return null;
    }
}
