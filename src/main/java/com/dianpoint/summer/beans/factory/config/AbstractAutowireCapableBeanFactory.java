package com.dianpoint.summer.beans.factory.config;

import java.util.ArrayList;
import java.util.List;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.dianpoint.summer.beans.factory.support.AbstractBeanFactory;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/21 22:35
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
    implements AutowireCapableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public void addBeanPostProcessor(AutowiredAnnotationBeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public int getBeanPostProcessorCount() {
        return this.beanPostProcessors.size();
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : this.getBeanPostProcessors()) {
            beanPostProcessor.setBeanFactory(this);
            try {
                result = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
                if (result == null) {
                    return null;
                }
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            try {
                result = beanPostProcessor.postProcessAfterInitialization(result, beanName);
                if (result == null) {
                    return null;
                }
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}