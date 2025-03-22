package com.dianpoint.summer.aop;

/**
 * 默认的AOP Proxy代理类创建工厂,此处采用JDK动态代理来生成代理类
 *
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 20:58
 */
public class DefaultAopProxyFactory implements AopProxyFactory {
    @Override
    public AopProxy createAopProxy(Object target, Advisor advisor) {

        // 采用JDK dynamicProxy来实现代理类的创建
        return new JdkDynamicAopProxy(target, advisor);
    }
}
