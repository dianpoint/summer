package com.dianpoint.summer.aop;

import java.lang.reflect.Method;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 22:02
 */
public interface AfterReturningAdvice extends AfterAdvice {

    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}
