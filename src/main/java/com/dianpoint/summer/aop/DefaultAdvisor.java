package com.dianpoint.summer.aop;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 22:18
 */
public class DefaultAdvisor implements Advisor {

    private MethodInterceptor methodInterceptor;

    @Override
    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    @Override
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    @Override
    public Advice getAdvice() {
        return methodInterceptor;
    }
}
