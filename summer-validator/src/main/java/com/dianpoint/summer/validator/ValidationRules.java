package com.dianpoint.summer.validator;

import java.util.*;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/19 22:26
 */

public final class ValidationRules {

    private ValidationRules() {

    }

    public static ValidationRule<String> minLength(int length) {
        return str -> str.length() >= length ?
                ValidationResult.success() :
                ValidationResult.failure("长度不能小于" + length, "string");
    }

    public static ValidationRule<String> maxLength(int length) {
        return str -> str.length() <= length ?
                ValidationResult.success() :
                ValidationResult.failure("长度不能大于" + length, "string");
    }

    public static ValidationRule<String> matches(String regex) {
        return str -> str.matches(regex) ?
                ValidationResult.success() :
                ValidationResult.failure("格式不符合要求" + regex, "string");
    }

    public static ValidationRule<Integer> min(int value) {
        return num -> num >= value ?
                ValidationResult.success() :
                ValidationResult.failure("不能小于" + value, "integer");
    }

    public static ValidationRule<Integer> max(int value) {
        return num -> num <= value ?
                ValidationResult.success() :
                ValidationResult.failure("不能大于" + value, "integer");
    }

    //设置collections相关的校验规则
    public static <C extends Collection<?>> ValidationRule<C> minsize(int min) {
        return collection -> collection != null && collection.size() >= min ?
                ValidationResult.success() :
                ValidationResult.failure("集合大小不能小于" + min, "collection");
    }

    public static <C extends Collection<?>> ValidationRule<C> maxSize(int max) {
        return collection -> collection != null && collection.size() <= max ?
                ValidationResult.success() :
                ValidationResult.failure("集合大小不能大于" + max, "collection");
    }

    public static <C extends Collection<?>> ValidationRule<C> range(int min, int max) {
        return collection -> collection != null && collection.size() >= min && collection.size() <= max ?
                ValidationResult.success() :
                ValidationResult.failure(String.format("集合大小必须在[%s,%s]之间", min, max), "collection");
    }

    public static <E, C extends Collection<E>> ValidationRule<C> uniqueElements() {
        return collection -> {
            if (collection == null) {
                return ValidationResult.success();
            }
            Set<E> uniqueSet = new LinkedHashSet<>();
            List<E> duplicates = new ArrayList<>();

            for (E element : collection) {
                if (!uniqueSet.add(element)) {
                    //set中添加失败代表元素element已经存在
                    duplicates.add(element);
                }
            }
            return duplicates.isEmpty() ?
                    ValidationResult.success() :
                    ValidationResult.failure("集合中包含重复元素" + duplicates, "collection");

        };
    }


}
