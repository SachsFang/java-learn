package com.fang.springboot.common.functions_module.cache_query.annotation;

import com.fang.springboot.common.functions_module.cache_query.enums.CacheLevel;

import java.lang.annotation.*;

/**
 * @author shaobin
 * @date 2024/5/6 15:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CacheRemove {

    String targetMethod() default "";

    String[] targetMethods() default {};

    CacheLevel cacheLevel() default CacheLevel.MIXED;
}
