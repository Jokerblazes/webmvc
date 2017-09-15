package com.joker.webmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.joker.webmvc.utils.RequestMethod;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 注册请求url注解类
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public @interface RequestMapping {
	String value() default "";
	RequestMethod[] method() default {};
}
