package com.dianpoint.summer.validator.validator.collection;

import com.dianpoint.summer.validator.ValidationResult;
import com.dianpoint.summer.validator.ValidationRule;
import com.dianpoint.summer.validator.Validators;
import com.dianpoint.summer.validator.validator.AbstractValidator;
import com.dianpoint.summer.validator.validator.Validator;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/24 15:05
 */

public class DefaultCollectionValidator<E, C extends Collection<E>> extends AbstractValidator<C> implements CollectionValidator<E, C> {

    private Validator<E> elementValidator;

    public DefaultCollectionValidator(Class<C> collectionType, Class<E> elementType) {
        addRule(Objects::nonNull, "集合不能为null", "collection");
    }


    @Override
    public CollectionValidator<E, C> addRule(ValidationRule<C> rule) {
        super.addRule(rule);
        return this;
    }

    @Override
    public CollectionValidator<E, C> addRule(Predicate<C> condition, String errorMessage) {
        super.addRule(condition, errorMessage);
        return this;
    }

    @Override
    public CollectionValidator<E, C> addRule(Predicate<C> condition, String errorMessage, String fieldName) {
        super.addRule(condition, errorMessage, fieldName);
        return this;
    }

    @Override
    public CollectionValidator<E, C> elementRule(ValidationRule<E> rule) {
        if (elementValidator == null) {
            //初始化元素校验器
            elementValidator = Validators.generic();
        }
        elementValidator.addRule(rule);
        return this;
    }

    @Override
    public CollectionValidator<E, C> elementRule(Predicate<E> condition, String errorMessage) {
        return elementRule(target -> condition.test(target) ? ValidationResult.success() : ValidationResult.failure(errorMessage));
    }

    @Override
    public CollectionValidator<E, C> elementValidator(Validator<E> validator) {
        elementValidator = validator;
        return this;
    }


    @Override
    public List<ValidationResult> validate(C collection) {
        List<ValidationResult> results = super.validate(collection);
        if (collection == null || collection.isEmpty()) {
            return results;
        }

        if (elementValidator == null) {
            return results;
        }
        int index = 0;
        for (E element : collection) {
            List<ValidationResult> elementResults = elementValidator.validate(element);
            for (ValidationResult elementResult : elementResults) {
                if (elementResult.isFailure()) {
                    results.add(ValidationResult.failure(String.format("元素[%s]:%s collection[%s]", index
                            , elementResult.getErrorMessage(), index), "collection"));
                }
            }
            index++;
        }
        return results;
    }
}
