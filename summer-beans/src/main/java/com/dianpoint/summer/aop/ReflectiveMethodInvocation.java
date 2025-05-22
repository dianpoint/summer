package com.dianpoint.summer.aop;

import java.lang.reflect.Method;

public class ReflectiveMethodInvocation implements MethodInvocation {

    private final Object proxy;
    private final Object target;
    private final Object[] arguments;
    private final Method method;
    private Class<?> targetClass;

    public ReflectiveMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass) {
        this.proxy = proxy;
        this.target = target;
        this.arguments = arguments;
        this.targetClass = targetClass;
        this.method = method;
    }


    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    public Object getProxy() {
        return proxy;
    }

    public Object getTarget() {
        return target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public Object proceed() throws Throwable {
        return this.method.invoke(this.target, this.arguments);
    }
}
