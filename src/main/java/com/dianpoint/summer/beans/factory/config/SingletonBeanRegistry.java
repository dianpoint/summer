package com.dianpoint.summer.beans.factory.config;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 15:09
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();
}
