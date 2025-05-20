package com.dianpoint.summer.validation.validator;

import com.dianpoint.summer.validation.ValidationResult;
import com.dianpoint.summer.validation.ValidationRule;
import com.dianpoint.summer.validation.Validators;
import com.dianpoint.summer.validation.processor.AnnotationProcessor;
import com.sun.tools.javac.comp.Todo;

import java.util.Collections;
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
        //TODO 处理注解类校验
        return Collections.emptyList();
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
