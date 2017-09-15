package com.joker.webmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Restful注解类
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public @interface ResponseBody {

}
