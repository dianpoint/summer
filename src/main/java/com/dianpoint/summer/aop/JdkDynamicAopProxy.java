package com.dianpoint.summer.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK Dynamic Proxy 实现AOP
 * 
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 21:04
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    Object target;

    public JdkDynamicAopProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(JdkDynamicAopProxy.class.getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理类执行之前");
        Object invokeResult = method.invoke(target, args);
        System.out.println("代理类执行之后");
        return invokeResult;
    }
}
