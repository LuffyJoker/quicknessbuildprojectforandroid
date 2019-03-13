package com.peng.commonlib.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

/**
 * Created by Mr.Q on 2019/2/21.
 *
 * 描述：测试数据库使用方法的类，即 DemoEntity
 *      1、@Entity 用来注解实体类
 *      2、@Database 通过 entities 属性引用被 @Entity 注解的类，并利用该类的所有字段作为表的列名来创建表
 *      3、Room 默认使用类名作为表名，使用字段名作为列名，但是，可以通过 @Entity 的 tableName 属性指定表名
 *      4、@ColumnInfo 的 name 属性指定列名
 *      5、每一个实体至少定义一个字段作为主键，即 @PrimaryKey
 *      6、可以将 @PrimaryKey 的 autoGenerate 属性设置为 true 来设置自动 id
 *      7、可以使用 @Entity 的 primaryKeys 属性来指定主键，如下:  @Entity 的 primaryKeys 属性
 *      8、为数据库添加索引可以加快数据的查询，如下: @Entity 的 indices 属性
 *      9、通过将 @Index 的 unique 属性设置为true，来确保唯一性
 *      10、Room 可以在两个实体之间定义外键。Book 为另一个表，Question 为外键表
 *          @Entity(foreignKeys = @ForeignKey(entity = Question.class, parentColumns = "id", childColumns = "user_id"))
 *          class Book {
 *              @PrimaryKey
 *              public int bookId;
 *
 *              public String title;
 *
 *              @ColumnInfo(name = "user_id")
 *              public int userId;
 *          }
 *      11、@Embedded 允许在一个实体中嵌入另外一个实体，创建的表使用的是当前实体和嵌入实体的所有字段，当一个类中嵌套多个类，并且这些类具有相同的字段，可以通过 @Embedded(prefix = "first") 添加一个前缀，生成的列名为（前缀+列名）
 *
 *
 */

@Entity(
        tableName = "User"
//        primaryKeys = {"id", "questionText"},
//        indices = {
//                @Index("name"),// 指定单个索引
//                @Index(value = {"last_name", "address"}, unique = true) // 指定多个索引，为了利用多个字段确定唯一性
//        },
//        foreignKeys = @ForeignKey(entity = Question.class, parentColumns = "id", childColumns = "user_id")
)
public class User {

    public User(Long id, int age) {
        this.id = id;
        this.age = age;
    }

    @PrimaryKey(autoGenerate = true)
    public Long id;

    public int age;

//    public String firstName;
//
//    public String lastName;
//
//    @Ignore
//    Bitmap picture;
//
//    public String street;
//    public String state;
//    public String city;
//
//    @ColumnInfo(name = "post_code")
//    public int postCode;
}
