package com.dianpoint.summer.validator.processor;

import com.dianpoint.summer.validator.ValidationResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/20 19:30
 */

public interface AnnotationProcessor {

    /**
     * 判断处理器是否支持当前注解
     *
     * @param annotationType 注解类型
     * @return true or false
     */
    boolean support(Class<? extends Annotation> annotationType);

    /**
     * 对注解标记的目标对象进行校验，并且返回校验结果
     *
     * @param target     目标对象
     * @param field      属性
     * @param annotation 注解
     * @return 校验结果集合
     */
    List<ValidationResult> process(Object target, Field field, Annotation annotation);
}
