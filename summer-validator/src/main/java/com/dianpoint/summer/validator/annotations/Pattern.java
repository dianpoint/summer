package com.dianpoint.summer.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/20 21:11
 */

@HandlesAnnotation(value = Pattern.class)
public @interface Pattern {

    String regex();

    String message() default "格式不符合要求";
}
