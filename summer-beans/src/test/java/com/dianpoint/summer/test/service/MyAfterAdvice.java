package com.dianpoint.summer.test.service;

import com.dianpoint.summer.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class MyAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("interceptor执行结果需要打印这个");
    }
}
