package com.joker.webmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/**
 * Controller注解类
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public @interface Controller {
	String value() default "";
}
