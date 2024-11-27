package com.dianpoint.summer.aop;

import java.lang.reflect.Method;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 22:11
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    void before(Method method, Object[] args, Object target) throws Throwable;

}
