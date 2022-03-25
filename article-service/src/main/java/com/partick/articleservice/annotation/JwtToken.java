package com.partick.articleservice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解Token
 * @author partick_peng
 */
@Target({ElementType.METHOD,ElementType.TYPE}) //只能用在方法上
@Retention(RetentionPolicy.RUNTIME) //运行时注解
public @interface JwtToken {
    boolean required() default true;
}
