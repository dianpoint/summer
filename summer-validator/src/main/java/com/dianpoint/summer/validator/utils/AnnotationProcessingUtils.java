package com.dianpoint.summer.validator.utils;

import com.dianpoint.summer.validator.ValidationResult;
import com.dianpoint.summer.validator.processor.AnnotationProcessor;
import com.dianpoint.summer.validator.processor.AnnotationProcessorRegister;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/22 21:18
 */

public class AnnotationProcessingUtils {

    static {
        // 初始化时扫描并注册所有处理器
        AnnotationProcessorRegister.scanAndRegisterAnnotationProcessor("com.dianpoint.summer.validator");
    }

    // 处理对象上的校验注解
    public static <T> List<ValidationResult> processAnnotations(T object) {
        if (object == null) {
            return java.util.Collections.singletonList(
                    ValidationResult.failure("校验对象不能为null", "object"));
        }

        List<ValidationResult> results = new ArrayList<>();
        Class<?> clazz = object.getClass();

        // 处理类上的所有字段
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            results.addAll(processAnnotationsOnField(object, field));
        }

        return results;
    }

    private static List<ValidationResult> processAnnotationsOnField(Object target, Field field) {
        List<ValidationResult> results = new ArrayList<>();

        for (Annotation annotation : field.getAnnotations()) {
            // 处理直接注解
            results.addAll(processAnnotation(target, field, annotation));

            // 处理组合注解（注解上的注解）
            results.addAll(processMetaAnnotations(target, field, annotation));
        }

        return results;
    }

    private static List<ValidationResult> processAnnotation(Object target, Field field, Annotation annotation) {
        Class<? extends Annotation> annotationType = annotation.annotationType();
        AnnotationProcessor handler = AnnotationProcessorRegister.getProcessor(annotationType);

        if (handler != null) {
            return handler.process(target, field, annotation);
        }

        return java.util.Collections.emptyList();
    }

    private static List<ValidationResult> processMetaAnnotations(Object target, Field field, Annotation annotation) {
        List<ValidationResult> results = new ArrayList<>();

        // 获取注解上的元注解
        for (Annotation metaAnnotation : annotation.annotationType().getAnnotations()) {
            if (metaAnnotation.annotationType().getPackage().getName().startsWith("java.lang")) {
                continue; // 跳过Java内置注解
            }

            results.addAll(processAnnotation(target, field, metaAnnotation));
            results.addAll(processMetaAnnotations(target, field, metaAnnotation));
        }

        return results;
    }
}
