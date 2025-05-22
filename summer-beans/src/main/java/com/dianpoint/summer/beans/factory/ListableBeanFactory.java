package com.dianpoint.summer.beans.factory;

import com.dianpoint.summer.beans.BeansException;

import java.util.Map;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/23 16:57
 */
public interface ListableBeanFactory extends BeanFactory {

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

    String[] getBeanNamesForType(Class<?> type);

    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

}
