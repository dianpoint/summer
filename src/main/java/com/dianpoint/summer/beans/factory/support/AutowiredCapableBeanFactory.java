package com.dianpoint.summer.beans.factory.support;

import com.dianpoint.summer.beans.BeansException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/21 22:35
 */
public class AutowiredCapableBeanFactory extends AbstractBeanFactory {

    private final List<AutowiredAnnotationBeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public List<AutowiredAnnotationBeanPostProcessor> getBeanPostProcessors() {
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
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (AutowiredAnnotationBeanPostProcessor beanPostProcessor : this.getBeanPostProcessors()) {
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
    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (AutowiredAnnotationBeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
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
