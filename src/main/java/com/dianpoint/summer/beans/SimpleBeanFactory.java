package com.dianpoint.summer.beans;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 14:35
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);

    public SimpleBeanFactory() {}

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = this.getSingleton(beanName);
        if (singleton == null) {
            // 此时还没有这个bean的实例，则获取它的定义BeanDefinition来创建实例
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            if (beanDefinition == null) {
                throw new BeansException("No bean exists");
            }
            try {
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            this.registerBean(beanName, singleton);
        }
        return singleton;
    }

    @Override
    public boolean containsBean(String name) {
        return this.containsSingleton(name);
    }

    @Override
    public void registerBean(String beanName, Object object) {
        this.registerSingleton(beanName, object);
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public Class<?> getType(String name) {
        return null;
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanDefinition.getId(), beanDefinition);
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {

    }

    @Override
    public void removeBeanDefinition(String name) {

    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return null;
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return false;
    }
}
