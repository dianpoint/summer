package com.dianpoint.summer.aop;

import java.lang.reflect.Method;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 22:05
 */
public interface MethodInvocation {

    Method getMethod();

    Object[] getArguments();

    Object getThis();

    Object proceed() throws Throwable;

}
