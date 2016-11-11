package com.liu.qinziyou.common.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 拦截器集合注解，作用于方法或类名，可被子类继承
 * @author liujc
 *2013-3-1
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface InterceptorNames {
	InterceptorName[] value();
}
