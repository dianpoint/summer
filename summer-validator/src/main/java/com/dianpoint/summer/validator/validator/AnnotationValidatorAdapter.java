package com.dianpoint.summer.validator.validator;


import com.dianpoint.summer.validator.ValidationResult;
import com.dianpoint.summer.validator.ValidationRule;
import com.dianpoint.summer.validator.utils.AnnotationProcessingUtils;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/20 21:59
 */

public class AnnotationValidatorAdapter<T> implements Validator<T> {

    public AnnotationValidatorAdapter(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public static <T> AnnotationValidatorAdapter<T> create(Class<T> clazz) {
        return new AnnotationValidatorAdapter<T>(clazz);
    }

    private Class<T> targetClass;
    private boolean skipOnFirstFailure = false;

    @Override
    public Validator<T> addRule(ValidationRule<T> rule) {
        throw new UnsupportedOperationException("注解校验器不支持手动添加规则");
    }

    @Override
    public Validator<T> addRule(Predicate<T> condition, String errorMessage) {
        throw new UnsupportedOperationException("注解校验器不支持手动添加规则");
    }

    @Override
    public Validator<T> addRule(Predicate<T> condition, String errorMessage, String fieldName) {
        throw new UnsupportedOperationException("注解校验器不支持手动添加规则");
    }

    @Override
    public List<ValidationResult> validate(T target) {
        if (target == null) {
            return java.util.Collections.singletonList(
                    ValidationResult.failure("校验对象不能为null", "object"));
        }
        List<ValidationResult> results = AnnotationProcessingUtils.processAnnotations(target);

        if (skipOnFirstFailure && !results.isEmpty()) {
            return java.util.Collections.singletonList(results.get(0));
        }

        return results;
    }

    @Override
    public boolean isValid(T target) {
        return validate(target).isEmpty();
    }

    @Override
    public Validator<T> skipOnFirstFailure(boolean skip) {
        this.skipOnFirstFailure = skip;
        return this;
    }


}
