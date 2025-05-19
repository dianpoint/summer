package com.dianpoint.summer.validation;

import com.dianpoint.summer.validation.validator.GenericValidator;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/19 22:16
 */

public final class Validators {

    private Validators() {
    }

    public static <T> GenericValidator<T> generic() {
        return GenericValidator.create();
    }

    public static GenericValidator<String> string() {
        return (GenericValidator<String>) GenericValidator.<String>create()
                .addRule(str -> str != null && !str.trim().isEmpty(),
                        "字符串不能为空",
                        "string");
    }

    public static GenericValidator<Integer> integer() {
        return (GenericValidator<Integer>) GenericValidator.<Integer>create()
                .addRule(Objects::nonNull, "整数不能为空", "integer");
    }

    public <T> ValidationRule<T> rule(Predicate<T> condition, String errorMessage, String fieldName) {
        return target -> condition.test(target) ?
                ValidationResult.success() :
                ValidationResult.failure(errorMessage, fieldName);
    }


}
