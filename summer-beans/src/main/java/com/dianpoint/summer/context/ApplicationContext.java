package com.dianpoint.summer.context;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.beans.factory.ListableBeanFactory;
import com.dianpoint.summer.beans.factory.config.BeanFactoryPostProcessor;
import com.dianpoint.summer.beans.factory.config.ConfigurableBeanFactory;
import com.dianpoint.summer.beans.factory.config.ConfigurableListableBeanFactory;
import com.dianpoint.summer.core.env.Environment;
import com.dianpoint.summer.core.env.EnvironmentCapable;

/**
 * <p>
 * <ul>
 * <li>抽取ApplicationContext接口，实现更多关于上下文的内容,包括ConfigurableListableBeanFactory、Environment、refresh等操作</li>
 * <li>集成ApplicationEventPublisher接口，支持事件的发布与监听</li>
 * <li>新增AbstractApplicationContext 规范ApplicationContext接口的实现流程框架
 * interface-abstract-class模式。将每一步操作进行抽象规范处理，提供默认实现类，同时支持自定义</li>
 * <li>refresh完成刷新操作后进行事件发布</li>
 * </ul>
 * </p>
 * 
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/24 14:35
 */
public interface ApplicationContext
    extends EnvironmentCapable, ListableBeanFactory, ConfigurableBeanFactory, ApplicationEventPublisher {

    String getApplicationName();

    long getStartupDate();

    ConfigurableListableBeanFactory getBeanFactory();

    void setEnvironment(Environment environment);

    Environment getEnvironment();

    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor beanFactoryPostProcessor);

    void refresh() throws BeansException;

    void close();

    boolean isActive();
}
