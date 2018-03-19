package com.mvc.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018-03-16.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    /**
     * 表示参数的别名，必填
     * @return
     */
    String value();
}
