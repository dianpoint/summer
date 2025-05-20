package com.dianpoint.summer.validation.constraintvalidators;

import com.dianpoint.summer.validation.ValidationResult;
import com.dianpoint.summer.validation.annotations.NotNull;
import com.dianpoint.summer.validation.processor.AnnotationProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/20 21:21
 */

public class NotNullProcessor implements AnnotationProcessor {
    @Override
    public boolean support(Class<? extends Annotation> annotationType) {
        return NotNull.class.isAssignableFrom(annotationType);
    }

    @Override
    public List<ValidationResult> process(Object target, Field field, Annotation annotation) {
        NotNull notNull = (NotNull) annotation;
        try {
            field.setAccessible(true);
            Object value = field.get(target);
            if (value == null) {
                return Collections.singletonList(ValidationResult.failure(notNull.message(), field.getName()));
            }
        } catch (Exception e) {
            return Collections.singletonList(ValidationResult.failure("无法访问字段" + field.getName(), field.getName()));
        }
        return Collections.emptyList();
    }
}
