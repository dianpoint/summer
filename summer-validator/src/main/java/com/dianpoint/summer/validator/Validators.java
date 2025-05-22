package com.dianpoint.summer.validator;

import com.dianpoint.summer.validator.validator.AnnotationValidatorAdapter;
import com.dianpoint.summer.validator.validator.GenericValidator;
import com.dianpoint.summer.validator.validator.Validator;

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

    public static <T> ValidationRule<T> rule(Predicate<T> condition, String errorMessage, String fieldName) {
        return target -> condition.test(target) ?
                ValidationResult.success() :
                ValidationResult.failure(errorMessage, fieldName);
    }

    public static <T> Validator<T> annotated(Class<T> targetClass) {
        return AnnotationValidatorAdapter.create(targetClass);
    }


}
