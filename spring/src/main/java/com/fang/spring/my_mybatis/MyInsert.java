package com.fang.spring.my_mybatis;

import java.lang.annotation.*;

/**
 * @author shaobin
 * @date 2022/9/12 21:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyInsert {
    String value() default "";
}
