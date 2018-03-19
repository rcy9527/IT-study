package com.mvc.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018-03-16.
 */
@Target(ElementType.TYPE)// 指定直接可作用在接口、类、枚举、注解上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default "";
}
