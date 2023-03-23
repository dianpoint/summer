package com.dianpoint.summer.beans.factory.config;

import com.dianpoint.summer.beans.factory.ListableBeanFactory;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/23 16:57
 */
public interface ConfigurableListableBeanFactory
    extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {}
