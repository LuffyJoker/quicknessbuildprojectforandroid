package com.peng.commonlib.utils;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.peng.commonlib.data.network.entity.DemoEntity;

import java.util.List;

/**
 * create by Mr.Q on 2019/3/15.
 * 类介绍：
 *      全局的一个数据处理工具，数据存储在 SharedPreferences 中
 *          1、对象序列化成字符串，存在 SharedPreferences 中
 *          2、字符串反序列化成对象
 */
public class GlobalDataUtils {

    /**
     * 保存集合示例
     *
     * @param demoEntityList
     */
    public static void saveCurDemoEntityList(List<DemoEntity> demoEntityList) {
        String json = JsonUtils.toJson(demoEntityList);
        if (json == null) {
            throw new JsonSyntaxException("Json 解析错误！");
        }
        SPUtils.getInstance().put("demoEntityList", json);
    }

    /**
     * 获取集合示例
     */
    public static List<DemoEntity> getDemoEntityList() {
        String json = SPUtils.getInstance().getString("demoEntityList");
        if (TextUtils.isEmpty(json)) {
            throw new NullPointerException("没有获取到 API 公共请求参数");
        }
        List<DemoEntity> demoEntityList = (List<DemoEntity>) JsonUtils.fromJson(json, new TypeToken<List<DemoEntity>>() {}.getType());
        if (demoEntityList == null) {
            throw new NullPointerException("API公共请求参数为Null");
        }
        return demoEntityList;
    }

    /**
     * 存储对象示例
     * @param demoEntity
     */
    public static void saveDemoEntity(DemoEntity demoEntity) {
        String json = JsonUtils.toJson(demoEntity);
        if (json == null) {
            throw new JsonSyntaxException("Json 解析错误！");
        }
        SPUtils.getInstance().put("demoEntity",json);
    }
    /**
     * 获取对象示例
     */
    public static DemoEntity getRequestCom() {
        String json = SPUtils.getInstance().getString("demoEntity");
        if (TextUtils.isEmpty(json)) {
            throw new NullPointerException("没有获取到API公共请求参数");
        }
        DemoEntity demoEntity = JsonUtils.fromJson(json, DemoEntity.class);
        if (demoEntity == null) {
            throw new NullPointerException("API公共请求参数为Null");
        }
        return demoEntity;
    }
}
