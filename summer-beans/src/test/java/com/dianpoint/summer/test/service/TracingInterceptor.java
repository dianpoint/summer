package com.dianpoint.summer.test.service;

import com.dianpoint.summer.aop.MethodInterceptor;
import com.dianpoint.summer.aop.MethodInvocation;

public class TracingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("method: " + invocation.getMethod() + "is called on " + invocation.getThis() + "with args " + invocation.getArguments());
        Object result = invocation.proceed();
        System.out.println("method: " + invocation.getMethod() + "result :" + result);
        return result;
    }
}
