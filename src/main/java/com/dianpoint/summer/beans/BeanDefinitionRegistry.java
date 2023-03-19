package com.dianpoint.summer.beans;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 15:20
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);

    void removeBeanDefinition(String name);

    BeanDefinition getBeanDefinition(String name);

    boolean containsBeanDefinition(String name);
}
