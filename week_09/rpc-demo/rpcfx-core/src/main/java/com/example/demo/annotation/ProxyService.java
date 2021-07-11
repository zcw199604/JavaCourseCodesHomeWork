package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * service 初始化注解
 *
 * group,version,targs 都有默认值，是为了兼容以前的版本
 *
 * @author lw1243925457
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProxyService {
    /**
     * 对应 API 接口名称
     * @return API service
     */
    String service();

    /**
     * 分组
     * @return group
     */
    String group() default "default";

    /**
     * version
     * @return version
     */
    String version() default "default";

    /**
     * tags:用于简单路由
     * 多个标签使用逗号分隔
     * @return tags
     */
    String tags() default "";

}
