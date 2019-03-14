package com.peng.commonlib.data.entity;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by Mr.Q on 2019/2/21.
 * 描述：
 */
public class NameTuple {
    @ColumnInfo(name="first_name")
    public String firstName;

    @ColumnInfo(name="last_name")
    public String lastName;
}
