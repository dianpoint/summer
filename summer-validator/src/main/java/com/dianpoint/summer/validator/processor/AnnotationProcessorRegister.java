package com.dianpoint.summer.validator.processor;

import com.dianpoint.summer.validator.annotations.HandlesAnnotation;
import com.dianpoint.summer.validator.annotations.NotNull;
import com.dianpoint.summer.validator.annotations.Pattern;
import com.dianpoint.summer.validator.constraintvalidators.NotNullProcessor;
import com.dianpoint.summer.validator.constraintvalidators.PatternProcessor;
import com.dianpoint.summer.validator.utils.ClassScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/20 20:59
 */

public class AnnotationProcessorRegister {

    private static final Map<Class<? extends Annotation>, AnnotationProcessor> PROCESSORS = new HashMap<>();

    public static void register(Class<? extends Annotation> annotationType, AnnotationProcessor annotationProcessor) {
        PROCESSORS.put(annotationType, annotationProcessor);
    }

    public static AnnotationProcessor getProcessor(Class<? extends Annotation> annotationType) {
        return PROCESSORS.get(annotationType);
    }

    public static boolean hasProcessor(Class<? extends Annotation> annotationType) {
        return PROCESSORS.containsKey(annotationType);
    }

    public static void scanAndRegisterAnnotationProcessor(String packageName) {
        try {
            ClassScanner classScanner = new ClassScanner();
            Set<Class<?>> classes = classScanner.scan(packageName);
            for (Class<?> clazz : classes) {
                if (AnnotationProcessor.class.isAssignableFrom(clazz) &&
                        !Modifier.isAbstract(clazz.getModifiers())) {

                    HandlesAnnotation annotation = clazz.getAnnotation(HandlesAnnotation.class);
                    if (annotation != null) {
                        AnnotationProcessor handler = (AnnotationProcessor) clazz.getDeclaredConstructor().newInstance();
                        register(annotation.value(), handler);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("扫描注解处理器失败", e);

        }
    }


}
