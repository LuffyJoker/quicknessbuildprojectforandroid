package com.peng.commonlib.daggerinject.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Mr.Q on 2019/2/20.
 * 描述：
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseName {
}
