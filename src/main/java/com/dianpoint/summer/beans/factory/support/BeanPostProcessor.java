package com.dianpoint.summer.beans.factory.support;

import com.dianpoint.summer.beans.BeansException;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/21 22:30
 */
public interface BeanPostProcessor {

    /**
     * bean初始化之前
     * 
     * @param bean
     *            待初始化的Bean
     * @param beanName
     *            BeanName
     * @return result
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * bean初始化之后执行
     * 
     * @param bean
     *            初始化完成Bean
     * @param beanName
     *            BeanName
     * @return result
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
