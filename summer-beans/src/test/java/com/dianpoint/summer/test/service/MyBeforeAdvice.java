package com.dianpoint.summer.test.service;

import com.dianpoint.summer.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截器执行之前需要打印这个");
    }
}
