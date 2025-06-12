package com.dianpoint.summer.validator.annotations;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/20 21:10
 */

@HandlesAnnotation(value = NotNull.class)
public @interface NotNull {

    String message() default "不能为空";
}
