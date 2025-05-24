package com.dianpoint.summer.validator.test;


import com.dianpoint.summer.validator.ValidationResult;
import com.dianpoint.summer.validator.ValidationRules;
import com.dianpoint.summer.validator.Validators;
import com.dianpoint.summer.validator.test.cases.User;
import com.dianpoint.summer.validator.validator.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

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

//        validateObject();

//        validateCollectionString();

//        validateCollectionObjects();

        validateAnnotation();
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


    static void validateCollectionString() {
        List<String> strList = Arrays.asList("张三", "李四", "", null, "王五", "小王");
        List<ValidationResult> validationResults = Validators.collection(List.class, String.class)
                .addRule(ValidationRules.minsize(1))
                .addRule(ValidationRules.maxSize(10))
                .addRule(ValidationRules.range(3, 5))
                .elementRule(notNull(), "元素不等为null")
                .elementRule(notEmpty(), "元素不能为空字符串")
                .validate(strList);

        printResults(validationResults);
    }

    static void validateCollectionObjects() {
        List<User> users = Arrays.asList(
                new User("张三", "123@mail.com"),
                new User("", "1234@gmail.com"),
                new User(null, ""),
                null
        );

        Validator<User> userValidators = Validators.<User>generic()
                .addRule(user -> user.getUsername() != null && !user.getUsername().isEmpty(),
                        "用户名不能为空")
                .addRule(user -> user.getEmail() != null && user.getEmail().contains("@"), "邮箱格式不正确");

        List<ValidationResult> validationResults = Validators.collection(List.class, User.class).elementValidator(userValidators).validate(users);
        printResults(validationResults);
    }

    static void validateAnnotation() {
        User user = new User(null, "123@mail.com");
        List<ValidationResult> validationResults = Validators.annotated(User.class).validate(user);
        printResults(validationResults);
    }


    static void printResults(List<ValidationResult> validateResult) {
        for (ValidationResult result : validateResult) {
            System.out.println(result.getFieldName() + "校验结果:" + result.getErrorMessage());
        }
    }


    //辅助断言工具

    static Predicate<String> notEmpty() {
        return str -> !str.isEmpty();
    }

    static Predicate<String> notNull() {
        return Objects::nonNull;
    }

    static Predicate<String> notBlank() {
        return notEmpty().and(notNull());
    }
}
