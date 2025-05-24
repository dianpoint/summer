package com.dianpoint.summer.validator.annotations;

import java.lang.annotation.*;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/22 21:30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandlesAnnotation {

    Class<? extends Annotation> value();
}
