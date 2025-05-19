package com.dianpoint.summer.validation;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/19 21:48
 */

public class ValidationResult {
    private final boolean success;
    private final String errorMessage;
    private final String fieldName;

    public ValidationResult(boolean success, String errorMessage, String fieldName) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.fieldName = fieldName;
    }

    public static ValidationResult success() {
        return new ValidationResult(true, null, null);
    }

    public static ValidationResult failure(String errorMessage) {
        return new ValidationResult(false, errorMessage, null);
    }

    public static ValidationResult failure(String errorMessage, String fieldName) {
        return new ValidationResult(false, errorMessage, fieldName);
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFailure() {
        return !success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }
}
