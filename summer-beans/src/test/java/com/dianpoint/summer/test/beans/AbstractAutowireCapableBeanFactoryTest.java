package com.dianpoint.summer.test.beans;

import com.dianpoint.summer.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.dianpoint.summer.beans.factory.config.AbstractAutowireCapableBeanFactory;
import com.dianpoint.summer.beans.factory.config.BeanDefinition;
import com.dianpoint.summer.beans.factory.support.DefaultListableBeanFactory;
import com.dianpoint.summer.test.xml.beans.AutowiredService;
import com.dianpoint.summer.test.xml.beans.BaseService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author wangyi
 * @date 2023/3/29
 */
public class AbstractAutowireCapableBeanFactoryTest {
    static AbstractAutowireCapableBeanFactory abstractAutowireCapableBeanFactory;



    @BeforeClass
    public static void init(){
        abstractAutowireCapableBeanFactory = new DefaultListableBeanFactory();
        // 设置autowired的postProcessor
        abstractAutowireCapableBeanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
        // 分别注册baseService和autowireService
        // baseService持有autowireService的@Autowired

        // 需要先注册beanDefinition，再注册bean
        abstractAutowireCapableBeanFactory.registerBeanDefinition("baseService",new BeanDefinition("baseService", BaseService.class.getName()));
        abstractAutowireCapableBeanFactory.registerBeanDefinition("autowiredService",new BeanDefinition("autowiredService", AutowiredService.class.getName()));

        abstractAutowireCapableBeanFactory.registerBean("baseService",new BaseService());
        abstractAutowireCapableBeanFactory.registerBean("autowiredService",new AutowiredService());
    }


    @Test
    public void testGetBeanPostProcessorCount() throws Exception {
        // init初始化了一条
        final int result = abstractAutowireCapableBeanFactory.getBeanPostProcessorCount();
        Assert.assertEquals(1, result);
    }

    @Test
    public void testApplyBeanPostProcessorsBeforeInitialization() throws Exception {
        final BaseService baseService = (BaseService)abstractAutowireCapableBeanFactory.getBean("baseService");
        final Object result = abstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization(baseService, "baseService");
        Assert.assertEquals(baseService, result);
    }


    @Test
    public void testApplyBeanPostProcessorsBeforeInitialization_errorBeanNameCase() throws Exception {
        final BaseService baseService = (BaseService)abstractAutowireCapableBeanFactory.getBean("baseService");
        // 方法的beanName无效
        final Object result = abstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization(baseService, "baseService_withError");
        Assert.assertEquals(baseService, result);
    }


    @Test
    public void testApplyBeanPostProcessorsAfterInitialization() throws Exception {
        final BaseService baseService = (BaseService)abstractAutowireCapableBeanFactory.getBean("baseService");
        final Object result = abstractAutowireCapableBeanFactory.applyBeanPostProcessorsAfterInitialization(baseService, "baseService");
        Assert.assertNull( result);
    }


    @Test
    public void testGetDependentBeans() throws Exception {
        final String[] result = abstractAutowireCapableBeanFactory.getDependentBeans("beanName");
        Assert.assertArrayEquals(new String[]{}, result);
    }

    @Test
    public void testGetDependenciesForBean() throws Exception {
        final String[] result = abstractAutowireCapableBeanFactory.getDependenciesForBean("beanName");
        Assert.assertArrayEquals(new String[]{}, result);
    }

}