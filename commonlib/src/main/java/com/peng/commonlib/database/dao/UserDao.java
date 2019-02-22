package com.peng.commonlib.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.peng.commonlib.database.entity.NameTuple;
import com.peng.commonlib.database.entity.User;

import java.util.List;

import javax.inject.Inject;

import dagger.Provides;
import io.reactivex.Flowable;

/**
 * Created by Mr.Q on 2019/2/21.
 * 描述：
 *      数据库访问类
 *      1、onConflict 用来指定当发生冲突时的策略
 *          OnConflictStrategy.ROLLBACK
 *          OnConflictStrategy.ABORT 终止并崩溃
 *          OnConflictStrategy.FAIL
 *          OnConflictStrategy.IGNORE
 *          OnConflictStrategy.REPLACE 当发生冲突时替换老数据
 *      2、@Insert 注解的方法可以设置返回值，该值为插入新条目的 rowID，如果一次性插入一个集合或多个参数时，返回 Long[] 或者 List<Long>
 *      3、@Delete 注解的方法还可以返回 int，表示删除的行数
 */
@Dao
public interface UserDao{

    /**
     * 在插入操作加入冲突处理策略，如果是相同的项，则进行替换
     *
     * @author pq
     * create at 2019/2/21
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(User... users);

    /**
     * 插入两个 user
     *
     * @author pq
     * create at 2019/2/21
     */
    @Insert
    void insertBothUsers(User user1, User user2);

    /**
     * 插入一项、插入一个集合
     *
     * @author pq
     * create at 2019/2/21
     */
    @Insert
    void insertUsersAndFriends(User user, List<User> friends);

    /**
     * 更新
     *
     * @author pq
     * create at 2019/2/21
     */
    @Update
    void updateUsers(User... users);

    /**
     * 删除所有 users
     *
     * @author pq
     * create at 2019/2/21
     */
    @Delete
    void deleteUsers(User... users);

    /**
     * 查询 User 表，User 为表名，在创建 User 类时，用@Entity(tableName = "User")
     *
     * @author pq
     * create at 2019/2/21
     */
    @Query("SELECT * FROM User WHERE id = :userID")
    User queryUserByUserId(String userID);

    /**
     * 查询 User 表，User 为表名，在创建 User 类时，用@Entity(tableName = "User")
     *
     * @author pq
     * create at 2019/2/21
     */
    @Query("SELECT * FROM User")
    User[] loadAllUsers();

    /**
     * 传入单个参数，查询满足 age > minAge 的所有项
     *
     * @author pq
     * create at 2019/2/21
     */
    @Query("SELECT * FROM User WHERE age > :minAge")
    User[] loadAllUsersOlderThan(int minAge);


    /**
     * 传入两个参数，查询满足 maxAge > age > minAge 的所有项
     *
     * @author pq
     * create at 2019/2/21
     */
    @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
    User[] loadAllUsersBetweenAges(int minAge, int maxAge);

    /**
     * 模糊查询，firstName 或者 lastName 包含 search 即可
     *
     * @author pq
     * create at 2019/2/21
     */
    @Query("SELECT * FROM user WHERE firstName LIKE :search " + "OR lastName LIKE :search")
    List<User> findUserWithName(String search);

    /**
     * 如果只需要获取实体的少数几个字段
     * 则查询 firstName、lastName 字段即可，用包含对应的对象的集合进行接收即可
     * WHERE 是查询条件
     *
     * @author pq
     * create at 2019/2/21
     */
    @Query("SELECT firstName, lastName FROM user WHERE region IN (:regions)")
    List<NameTuple> loadUsersFromRegions(List<String> regions);

    /**
     * RxJava2 支持，Flowable 为支持背压操作的被观察者
     * 要支持 RxJava2，需要添加依赖：android.arch.persistence.room:rxjava2
     *
     * @author pq
     * create at 2019/2/21
     */
    @Query("SELECT * from user where id = :id LIMIT 1")
    Flowable<User> loadUserById(int id);

    /**
     *  直接返回Cursor
     *
     * @author pq
     * create at 2019/2/21
     */
    @Query("SELECT * FROM user WHERE age > :minAge LIMIT 5")
    Cursor loadRawUsersOlderThan(int minAge);

    /**
     * 查询多个表
     *
     * @author pq
     * create at 2019/2/21
     */
//    @Query("SELECT * FROM book "
//            + "INNER JOIN loan ON loan.book_id = book.id "
//            + "INNER JOIN user ON user.id = loan.user_id "
//            + "WHERE user.name LIKE :userName")
//    List<Book> findBooksBorrowedByNameSync(String userName);


}
