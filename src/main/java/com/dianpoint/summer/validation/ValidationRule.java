package com.dianpoint.summer.validation;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/19 21:48
 */

@FunctionalInterface
public interface ValidationRule<T> {

    ValidationResult validate(T target);

    // 默认方法：组合多个规则（AND关系）
    default ValidationRule<T> and(ValidationRule<T> other) {
        return target -> {
            ValidationResult result = this.validate(target);
            return result.isSuccess() ? other.validate(target) : result;
        };
    }

    // 默认方法：组合多个规则（OR关系）
    default ValidationRule<T> or(ValidationRule<T> other) {
        return target -> {
            ValidationResult result = this.validate(target);
            return result.isSuccess() ? result : other.validate(target);
        };
    }
}
