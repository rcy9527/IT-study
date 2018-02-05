package aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018-02-05.
 */
// 前置通知注解
@Target(ElementType.METHOD)//方法
@Retention(RetentionPolicy.RUNTIME)
public @interface Before {
    String value();
}
