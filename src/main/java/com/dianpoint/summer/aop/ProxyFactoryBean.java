package com.dianpoint.summer.aop;

import com.dianpoint.summer.beans.factory.FactoryBean;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 21:18
 */
public class ProxyFactoryBean implements FactoryBean<Object> {

    private AopProxyFactory aopProxyFactory;

    private Object target;

    private Object singletonInstance;

    public ProxyFactoryBean() {
        // 默认才用Jdk Dynamic 代理来实现AOP 可在此进行扩展
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }

    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    public AopProxy createAopProxy() {
        return getAopProxyFactory().createAopProxy(target);
    }

    /**
     * 通过AopProxy接口获取代理类,实现方式为AopProxy接口实现的DefaultAopProxyFactory或者其余可扩展的代理工厂类
     * 
     * @param aopProxy
     *            aopProxy
     * @return 代理结果
     */
    public Object getProxy(AopProxy aopProxy) {
        return aopProxy.getProxy();
    }

    /**
     * 获取代理对象的单例bean
     * 
     * @return 单例Bean
     */
    public synchronized Object getSingletonInstance() {
        if (this.singletonInstance == null) {
            AopProxy aopProxy = createAopProxy();
            this.singletonInstance = getProxy(aopProxy);
        }
        return this.singletonInstance;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public AopProxyFactory getAopProxyFactory() {
        return aopProxyFactory;
    }

    public void setAopProxyFactory(AopProxyFactory aopProxyFactory) {
        this.aopProxyFactory = aopProxyFactory;
    }

}
