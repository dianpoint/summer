package com.dianpoint.summer.validation.validator;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/19 22:15
 */

public class GenericValidator<T> extends AbstractValidator<T> {

    private GenericValidator() {
    }

    public static <T> GenericValidator<T> create() {
        return new GenericValidator<T>();
    }
}
