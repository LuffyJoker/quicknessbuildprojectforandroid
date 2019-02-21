package com.peng.commonlib.daggerinject.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 *      Retention 该注解决定 DatabaseVersion 这个注解的声明周期，有以下三种：
 *          RetentionPolicy.RUNTIME：让 DatabaseVersion 注解只在源文件中存在，编译成 .class 文件后，就不存在了
 *          RetentionPolicy.CLASS：让 DatabaseVersion 注解在 java 源文件(.java文件)中存在，编译成 .class 文件后注解也还存在，加载到内存后不存在
 *          RetentionPolicy.RUNTIME：一直都在
 *
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseVersion {
}
