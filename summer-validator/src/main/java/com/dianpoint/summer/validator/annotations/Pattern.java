package com.dianpoint.summer.validator.annotations;

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
