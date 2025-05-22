package com.dianpoint.summer.test.xml;

import com.dianpoint.summer.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.dianpoint.summer.beans.factory.config.BeanFactoryPostProcessor;
import com.dianpoint.summer.context.ClassPathXmlApplicationContext;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * @author wangyi
 * @date 2023/3/31
 */
public class ClassPathXmlApplicationContextTest {
    static ClassPathXmlApplicationContext classPathXmlApplicationContext;
    static String beanNames = "registerBeanStr";
    static String beanObject = "beanObject";

    @Before
    public void setUp() {
        classPathXmlApplicationContext = new ClassPathXmlApplicationContext("xml/beanAutowire.xml",false);
        classPathXmlApplicationContext.registerBean(beanNames, beanObject);
    }

    @Test
    public void testRegisterBean() {
        final String[] names = classPathXmlApplicationContext.getBeanFactory().getSingletonNames();
        assertThat(names).contains(beanNames);
        assertThat(classPathXmlApplicationContext.getBeanFactory().containsBean(beanNames)).isTrue();
    }

    @Test
    public void testGetSingleton() {
        final Object result = classPathXmlApplicationContext.getSingleton(beanNames);
        assertThat(result).isEqualTo(beanObject);
    }

    @Test
    public void testAddBeanPostProcessor() {
        classPathXmlApplicationContext.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
        final Integer count = classPathXmlApplicationContext.getBeanPostProcessorCount();
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void testGetBeanDefinitionCount() {
        final Integer result = classPathXmlApplicationContext.getBeanDefinitionCount();
        assertThat(result).isEqualTo(2);
    }


    @Test
    public void testAddBeanFactoryPostProcessor() {
        classPathXmlApplicationContext.addBeanFactoryPostProcessor(beanFactory -> {

        });
        final List<BeanFactoryPostProcessor> list = classPathXmlApplicationContext.getBeanFactoryPostProcessors();
        assertThat(!list.isEmpty()).isTrue();
    }

    @Test
    public void testIsActive() {
        final boolean result = classPathXmlApplicationContext.isActive();
        assertThat(result).isTrue();
    }
}
