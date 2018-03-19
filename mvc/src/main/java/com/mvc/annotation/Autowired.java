package com.mvc.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018-03-16.
 */
@Target({ElementType.FIELD,ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    String value() default "";
}
