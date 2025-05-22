package com.dianpoint.summer.beans.factory.config;

import com.dianpoint.summer.beans.factory.BeanFactory;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/23 16:59
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    int getBeanPostProcessorCount();

    void registerDependentBean(String beanName, String dependentBeanName);

    String[] getDependentBeans(String beanName);

    String[] getDependenciesForBean(String beanName);

}
