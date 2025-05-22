package com.dianpoint.summer.validator.test;


import com.dianpoint.summer.validator.ValidationResult;
import com.dianpoint.summer.validator.ValidationRules;
import com.dianpoint.summer.validator.Validators;
import com.dianpoint.summer.validator.test.cases.User;

import java.util.List;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/19 22:33
 */

public class ValidatorDemo {
    public static void main(String[] args) {
//        validateEmail("congccoder@gmail.com");
//        validateEmail("congccoder@dianpoint.com");
//
//        validateAge(16);
//        validateAge(21);

        validateObject();
    }

    static void validateEmail(String email) {
        List<ValidationResult> validateResult = Validators.string()
                .addRule(ValidationRules.minLength(1))
                .addRule(ValidationRules.maxLength(20))
                .addRule(str -> !str.endsWith("gmail.com"), "自定义错误提示信息", "email")
                .addRule(ValidationRules.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
                .validate(email);
        printResults(validateResult);
    }

    static void validateAge(Integer age) {
        List<ValidationResult> validationResults = Validators.integer()
                .addRule(ValidationRules.max(18))
                .addRule(ValidationRules.min(14))
                .addRule(num -> num <= 15, "自定义错误信息:青少年年龄", "age")
                .validate(age);
        printResults(validationResults);
    }

    static void validateObject() {
        User user = new User(null, "");
        List<ValidationResult> validationResults = Validators
                .annotated(User.class)
                .skipOnFirstFailure(false)
                .validate(user);
        printResults(validationResults);
    }


    static void printResults(List<ValidationResult> validateResult) {
        for (ValidationResult result : validateResult) {
            System.out.println(result.getFieldName() + "校验结果:" + result.getErrorMessage());
        }
    }
}
