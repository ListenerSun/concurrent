package com.sqt.concurrent.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 线程不安全类注解
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-09-29 1:48
 */
@Target(ElementType.TYPE)
//注解的作用范围  RUNIME表示反射时可以拿到
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadUnsafe {

    String value() default "";
}
