package com.dianpoint.summer.validation;

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

}
