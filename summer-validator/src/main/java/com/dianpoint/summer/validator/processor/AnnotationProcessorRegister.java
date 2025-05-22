package com.dianpoint.summer.validator.processor;

import com.dianpoint.summer.validator.annotations.NotNull;
import com.dianpoint.summer.validator.annotations.Pattern;
import com.dianpoint.summer.validator.constraintvalidators.NotNullProcessor;
import com.dianpoint.summer.validator.constraintvalidators.PatternProcessor;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/20 20:59
 */

public class AnnotationProcessorRegister {

    private static final Map<Class<? extends Annotation>, AnnotationProcessor> PROCESSORS = new HashMap<>();

    static {
        PROCESSORS.put(NotNull.class, new NotNullProcessor());
        PROCESSORS.put(Pattern.class, new PatternProcessor());
    }

    public static void register(Class<? extends Annotation> annotationType, AnnotationProcessor annotationProcessor) {
        PROCESSORS.put(annotationType, annotationProcessor);
    }

    public static AnnotationProcessor getProcessor(Class<? extends Annotation> annotationType) {
        return PROCESSORS.get(annotationType);
    }

    public static boolean hasProcessor(Class<? extends Annotation> annotationType) {
        return PROCESSORS.containsKey(annotationType);
    }


}
