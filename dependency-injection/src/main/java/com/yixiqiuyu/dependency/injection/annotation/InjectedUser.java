package com.yixiqiuyu.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * @author yixiqiuyu
 * @Description
 * @Date 2022/5/17 22:11
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
