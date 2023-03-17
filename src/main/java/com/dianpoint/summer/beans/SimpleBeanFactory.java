package com.dianpoint.summer.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 14:35
 */
public class SimpleBeanFactory implements BeanFactory {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private List<String> beanNames = new ArrayList<>();

    private Map<String, Object> singletons = new HashMap<>();

    public SimpleBeanFactory() {}

    @Override
    public Object getBean(String beanName) throws NoSuchBeanDefinitionException {
        Object singleton = singletons.get(beanName);
        if (singleton == null) {
            // 此处单例创建未考虑DCL模式,后续优化
            int i = beanNames.indexOf(beanName);
            if (i == -1) {
                throw new NoSuchBeanDefinitionException();
            }
            BeanDefinition beanDefinition = beanDefinitions.get(i);
            try {
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            singletons.put(beanDefinition.getId(), singleton);
        }
        return singleton;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
        this.beanNames.add(beanDefinition.getId());
    }
}
