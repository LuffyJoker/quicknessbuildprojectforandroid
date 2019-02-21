package com.peng.commonlib.database.converter;

import android.arch.persistence.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 *      1、Room 支持字符串和基本数据类型以及他们的包装类
 *      2、如果不是基本类型以及它们的包装类，例如 Date 类型，则需要使用 @TypeConverter 将不可存储的类型转换为 Room 可以存储的类型
 *      3、使用范例如下：
 *      4、为了让 Room 调用该转换器，使用 @TypeConverters 注解将转换器添加到 AppDatabase
 */
public class DateConverter {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @TypeConverter
    public Date string2Date(String dateString) {
        try {
            return simpleDateFormat.parse(simpleDateFormat.format(new Date(Long.valueOf(dateString))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    String date2String(Date date) {
        String time = String.valueOf(date.getTime());
        return time;
    }
}
