package com.dianpoint.summer.aop;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 22:07
 */
public class AfterReturningAdviceInterceptor implements AfterAdvice, MethodInterceptor {

    private final AfterReturningAdvice advice;

    public AfterReturningAdviceInterceptor(AfterReturningAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Object reallyValue = invocation.proceed();
        this.advice.afterReturning(reallyValue, invocation.getMethod(), invocation.getArguments(),
            invocation.getThis());
        return reallyValue;
    }
}
