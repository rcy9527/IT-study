package com.mvc.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018-03-16.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
