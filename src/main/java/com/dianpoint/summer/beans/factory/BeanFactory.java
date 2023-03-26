package com.dianpoint.summer.beans.factory;

import com.dianpoint.summer.beans.BeansException;

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

    Object getBean(String beanName) throws BeansException;

    boolean containsBean(String name);

    void registerBean(String beanName, Object object);

    boolean isSingleton(String name);

    boolean isPrototype(String name);

    Class<?> getType(String name);
}
