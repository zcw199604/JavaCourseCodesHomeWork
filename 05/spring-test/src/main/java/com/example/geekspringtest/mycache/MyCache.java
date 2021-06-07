package com.example.geekspringtest.mycache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/11/17 8:04 下午
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCache {

    boolean open() default true;

    String seconds() default "60";
}
