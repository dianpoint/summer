package com.dianpoint.summer.aop;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 22:17
 */
public interface Advisor {

    MethodInterceptor getMethodInterceptor();

    void setMethodInterceptor(MethodInterceptor methodInterceptor);

    Advice getAdvice();
}
