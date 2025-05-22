package com.dianpoint.summer.validator.constraintvalidators;

import com.dianpoint.summer.validator.ValidationResult;
import com.dianpoint.summer.validator.annotations.Pattern;
import com.dianpoint.summer.validator.processor.AnnotationProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/20 21:25
 */

public class PatternProcessor implements AnnotationProcessor {
    @Override
    public boolean support(Class<? extends Annotation> annotationType) {
        return Pattern.class.isAssignableFrom(annotationType);
    }

    @Override
    public List<ValidationResult> process(Object target, Field field, Annotation annotation) {
        Pattern pattern = (Pattern) annotation;
        try {
            field.setAccessible(true);
            Object value = field.get(target);
            if (value == null) {
                return Collections.emptyList();
            }
            if (value instanceof String) {
                java.util.regex.Pattern regexPattern = java.util.regex.Pattern.compile(pattern.regex());
                if (!regexPattern.matcher(value.toString()).matches()) {
                    return Collections.singletonList(ValidationResult.failure(pattern.message(), field.getName()));
                }
            }
        } catch (Exception e) {
            return Collections.singletonList(ValidationResult.failure("无法访问字段" + field.getName(), field.getName()));
        }
        return Collections.emptyList();
    }
}
