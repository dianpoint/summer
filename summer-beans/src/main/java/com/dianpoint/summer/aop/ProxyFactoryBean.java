package com.dianpoint.summer.aop;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.beans.factory.BeanFactory;
import com.dianpoint.summer.beans.factory.BeanFactoryAware;
import com.dianpoint.summer.beans.factory.FactoryBean;
import com.dianpoint.summer.util.ClassUtils;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 21:18
 */
public class ProxyFactoryBean implements FactoryBean<Object>, BeanFactoryAware {

    private BeanFactory beanFactory;
    private AopProxyFactory aopProxyFactory;
    private String interceptorName;
    private String targetName;
    private Object target;
    private ClassLoader proxyClassLoader = ClassUtils.getDefaultClassLoader();
    private Object singletonInstance;
    private Advisor advisor;


    public ProxyFactoryBean() {
        this.aopProxyFactory = new DefaultAopProxyFactory();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getObject() throws Exception {
        return getSingletonInstance();
    }

    private synchronized void initializeAdvisor() {
        if (this.interceptorName == null || this.beanFactory == null) {
            return;
        }
        Object advice = null;
        MethodInterceptor methodInterceptor = null;
        try {
            advice = this.beanFactory.getBean(this.interceptorName);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        if (advice instanceof BeforeAdvice) {
            methodInterceptor = new MethodBeforeAdviceInterceptor((MethodBeforeAdvice) advice);
        } else if (advice instanceof ThrowsAdvice) {
            methodInterceptor = new AfterThrowingAdviceInterceptor((ThrowsAdvice) advice);
        } else if (advice instanceof AfterReturningAdvice) {
            methodInterceptor = new AfterReturningAdviceInterceptor((AfterReturningAdvice) advice);
        } else if (advice instanceof AfterAdvice) {
            methodInterceptor = new AfterReturningAdviceInterceptor((AfterReturningAdvice) advice);
        } else if (advice instanceof AroundAdvice) {
            methodInterceptor = new AroundAdviceInterceptor((AroundAdvice) advice);
        } else if (advice instanceof MethodInterceptor) {
            methodInterceptor = (MethodInterceptor) advice;
        }

        advisor = new DefaultAdvisor();
        if (methodInterceptor != null) {
            advisor.addMethodInterceptor(methodInterceptor);
        }
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    public AopProxy createAopProxy() {
        return getAopProxyFactory().createAopProxy(target, this.advisor);
    }

    public Object getProxy(AopProxy aopProxy) {
        return aopProxy.getProxy();
    }

    public synchronized Object getSingletonInstance() {
        if (this.singletonInstance == null) {
            initializeAdvisor();
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
