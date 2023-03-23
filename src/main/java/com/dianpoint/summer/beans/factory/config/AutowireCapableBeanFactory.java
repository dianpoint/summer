package com.dianpoint.summer.beans.factory.config;

import com.dianpoint.summer.beans.factory.BeanFactory;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/23 17:04
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    int AUTOWIRED_NO = 0;
    int AUTOWIRED_BY_NAME = 1;
    int AUTOWIRED_BY_TYPE = 2;

    int AUTOWIRED_CONSTRUCTOR = 3;

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName);

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName);

}
