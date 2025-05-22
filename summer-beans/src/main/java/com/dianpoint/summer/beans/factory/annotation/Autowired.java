package com.dianpoint.summer.beans.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 定义autowired注解进行反射处理,自动注入初始化Bean
 * </p>
 *
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 16:58
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {

}
