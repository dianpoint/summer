package com.dianpoint.summer.beans.factory.config;

import com.dianpoint.summer.beans.factory.BeanFactory;
import com.dianpoint.summer.beans.BeansException;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/23 17:07
 */
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
