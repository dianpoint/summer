package com.dianpoint.summer.beans;

/**
 * <p>
 * Bean创建注册和获取的工厂接口
 * </p>
 *
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 11:53
 */
public interface BeanFactory {

    Object getBean(String beanName) throws NoSuchBeanDefinitionException;

    void registerBeanDefinition(BeanDefinition beanDefinition);
}
