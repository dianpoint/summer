package com.dianpoint.summer.beans.factory.annotation;

import com.dianpoint.summer.beans.factory.BeanFactory;
import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/21 22:33
 */
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    private BeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean isAnnotation = field.isAnnotationPresent(Autowired.class);
            if (isAnnotation) {
                // 根据属性名称查找同名的bean
                String fieldName = field.getName();
                Object autowiredObject = this.getBeanFactory().getBean(fieldName);
                field.setAccessible(true);
                try {
                    field.set(bean, autowiredObject);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

}
