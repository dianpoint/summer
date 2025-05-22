package com.dianpoint.summer.aop;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.beans.factory.BeanFactory;
import com.dianpoint.summer.beans.factory.FactoryBean;
import com.dianpoint.summer.util.ClassUtils;
import com.fasterxml.jackson.databind.util.ClassUtil;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 21:18
 */
public class ProxyFactoryBean implements FactoryBean<Object> {

    private BeanFactory beanFactory;
    private AopProxyFactory aopProxyFactory;
    private String interceptorName;
    private String targetName;
    private Object target;
    private ClassLoader proxyClassLoader = ClassUtils.getDefaultClassLoader();
    private Object singletonInstance;
    private Advisor advisor;


    public ProxyFactoryBean() {
        // 默认才用Jdk Dynamic 代理来实现AOP 可在此进行扩展
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }

    @Override
    public Object getObject() throws Exception {
        return null;
    }

    private synchronized void initializeAdvisor() {
        Object advice = null;
        MethodInterceptor methodInterceptor = null;
        try {
            advice = this.beanFactory.getBean(this.interceptorName);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        if (advice instanceof BeforeAdvice) {
            methodInterceptor = new MethodBeforeAdviceInterceptor((MethodBeforeAdvice) advice);
        } else if (advice instanceof AfterAdvice) {
            methodInterceptor = new AfterReturningAdviceInterceptor((AfterReturningAdvice) advice);
        } else if (advice instanceof MethodInterceptor) {
            methodInterceptor = (MethodInterceptor) advice;
        }

        advisor = new DefaultAdvisor();
        advisor.setMethodInterceptor(methodInterceptor);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    public AopProxy createAopProxy() {
        return getAopProxyFactory().createAopProxy(target, this.advisor);
    }

    /**
     * 通过AopProxy接口获取代理类,实现方式为AopProxy接口实现的DefaultAopProxyFactory或者其余可扩展的代理工厂类
     *
     * @param aopProxy aopProxy
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
