package com.dianpoint.summer.validator.validator;

import com.dianpoint.summer.validator.ValidationResult;
import com.dianpoint.summer.validator.ValidationRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/19 21:48
 */

public abstract class AbstractValidator<T> implements Validator<T> {

    private final List<ValidationRule<T>> rules = new ArrayList<>();
    private boolean skipOnFirstFailure = false;
    private boolean allowNull = false;
    private String nullErrorMessage = "对象不能为null";
    private String fieldName;

    @Override
    public Validator<T> addRule(ValidationRule<T> rule) {
        rules.add(rule);
        return this;
    }

    @Override
    public Validator<T> addRule(Predicate<T> condition, String errorMessage) {
        return addRule(target ->
                condition.test(target) ?
                        ValidationResult.success() :
                        ValidationResult.failure(errorMessage, fieldName));
    }

    @Override
    public Validator<T> addRule(Predicate<T> condition, String errorMessage, String fieldName) {
        return addRule(target ->
                condition.test(target) ?
                        ValidationResult.success() :
                        ValidationResult.failure(errorMessage, fieldName));
    }

    @Override
    public List<ValidationResult> validate(T target) {
        if (target == null) {
            return allowNull ?
                    new ArrayList<>() :
                    Collections.singletonList(ValidationResult.failure(nullErrorMessage, fieldName));
        }
        List<ValidationResult> results = new ArrayList<>();
        for (ValidationRule<T> rule : rules) {
            ValidationResult result = rule.validate(target);
            if (result.isFailure()) {
                results.add(result);
                if (skipOnFirstFailure) {
                    break;
                }
            }
        }
        return results;
    }

    @Override
    public boolean isValid(T target) {
        return validate(target).isEmpty();
    }

    @Override
    public Validator<T> skipOnFirstFailure(boolean skipOnFirstFailure) {
        this.skipOnFirstFailure = skipOnFirstFailure;
        return this;
    }

    public Validator<T> fieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public Validator<T> allowNull(boolean allowNull) {
        this.allowNull = allowNull;
        return this;
    }

    public Validator<T> nullErrorMessage(String nullErrorMessage) {
        this.nullErrorMessage = nullErrorMessage;
        return this;
    }
}
