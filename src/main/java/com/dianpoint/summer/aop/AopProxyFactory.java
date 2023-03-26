package com.dianpoint.summer.aop;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 20:57
 */
public interface AopProxyFactory {

    /**
     * 创建AopProxy
     * 
     * @param target
     *            被代理类
     * @return aopProxy代理接口,提供不同代理方式
     */
    AopProxy createAopProxy(Object target);

}
