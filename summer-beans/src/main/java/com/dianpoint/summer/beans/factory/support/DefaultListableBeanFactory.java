package com.dianpoint.summer.beans.factory.support;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.beans.factory.config.AbstractAutowireCapableBeanFactory;
import com.dianpoint.summer.beans.factory.config.BeanDefinition;
import com.dianpoint.summer.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/23 16:56
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
    implements ConfigurableListableBeanFactory {

    @Override
    public int getBeanDefinitionCount() {
        return this.beanDefinitions.size();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionNames.toArray(new String[this.beanDefinitionNames.size()]);
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        List<String> results = new ArrayList<>();
        for (String beanName : this.beanDefinitionNames) {
            BeanDefinition beanDefinition = this.getBeanDefinition(beanName);
            Class<?> classToMatch = beanDefinition.getClass();
            if (type.isAssignableFrom(classToMatch)) {
                results.add(beanName);
            }
        }
        return results.toArray(new String[results.size()]);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        String[] beanNames = getBeanNamesForType(type);
        Map<String, T> result = new LinkedHashMap<>(beanNames.length);
        for (String beanName : beanNames) {
            Object beanInstance = getBean(beanName);
            result.put(beanName, (T)beanInstance);
        }
        return result;
    }
}
