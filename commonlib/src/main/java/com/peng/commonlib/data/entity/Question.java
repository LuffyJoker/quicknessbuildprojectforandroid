package com.peng.commonlib.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Mr.Q on 2019/2/20.
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
 *
 */
@Entity(tableName = "question") // 仅指定了表名，下面为高级属性的使用
public class Question implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private Long id;

    @ColumnInfo(name = "question_text")
    private String questionText;

    @ColumnInfo(name = "question_img_url")
    private String imgUrl = "imgUrl";

    @ColumnInfo(name = "created_at")
    private String createdAt = "createdAt";

    @ColumnInfo(name = "updated_at")
    private String updatedAt = "updatedAt";

    public Question(Long id, String questionText, String imgUrl, String createdAt, String updatedAt) {
        this.id = id;
        this.questionText = questionText;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
