package com.dianpoint.summer.aop;

import java.lang.reflect.Method;

public interface MethodReturningAdvice extends AfterAdvice{

    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}
