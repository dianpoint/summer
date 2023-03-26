package com.dianpoint.summer.beans.factory.config;

import com.dianpoint.summer.beans.factory.ListableBeanFactory;

/**
 * <p>
 * 对于ListableBeanFactory 、AutowireCapableBeanFactory 、ConfigurableBeanFactory等接口能力的集合
 * </p>
 * 
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/23 16:57
 */
public interface ConfigurableListableBeanFactory
    extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {}
