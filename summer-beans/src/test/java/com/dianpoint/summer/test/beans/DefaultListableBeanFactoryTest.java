package com.dianpoint.summer.test.beans;

import com.dianpoint.summer.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.dianpoint.summer.beans.factory.config.BeanDefinition;
import com.dianpoint.summer.beans.factory.support.DefaultListableBeanFactory;
import com.dianpoint.summer.test.xml.beans.AutowiredService;
import com.dianpoint.summer.test.xml.beans.BaseService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * @author wangyi
 * @date 2023/3/28
 */
public class DefaultListableBeanFactoryTest {

    static DefaultListableBeanFactory defaultListableBeanFactory;

    @BeforeClass
    public static void init(){
        // 默认设置值
        defaultListableBeanFactory = new DefaultListableBeanFactory();
        // 注册若干 beanDefinition
        final BeanDefinition beanDefinition1 = new BeanDefinition("baseService1", BaseService.class.getName());
        final BeanDefinition beanDefinition2 = new BeanDefinition("baseService2", BaseService.class.getName());
        final BeanDefinition beanDefinition3 = new BeanDefinition("baseService3", BaseService.class.getName());
        final BeanDefinition beanDefinition4 = new BeanDefinition("baseService4", BaseService.class.getName());
        defaultListableBeanFactory.registerBeanDefinition("baseService1",beanDefinition1);
        defaultListableBeanFactory.registerBeanDefinition("baseService2",beanDefinition2);
        defaultListableBeanFactory.registerBeanDefinition("baseService3",beanDefinition3);
        defaultListableBeanFactory.registerBeanDefinition("baseService4",beanDefinition4);
        // 注册beans
        defaultListableBeanFactory.registerBean("baseService1",beanDefinition1);
        defaultListableBeanFactory.registerBean("baseService2",beanDefinition2);
        defaultListableBeanFactory.registerBean("baseService3",beanDefinition3);
        defaultListableBeanFactory.registerBean("baseService4",beanDefinition4);
        // 覆盖一条
        defaultListableBeanFactory.registerBean("baseService4",beanDefinition4);
    }


    @Test
    public void testGetBeanDefinitionCount() throws Exception {
        final int result = defaultListableBeanFactory.getBeanDefinitionCount();
        Assert.assertEquals(4, result);
    }

    @Test
    public void testGetBeanDefinitionNames() throws Exception {
        final String[] result = defaultListableBeanFactory.getBeanDefinitionNames();
        Assert.assertArrayEquals(new String[]{"baseService1","baseService2","baseService3","baseService4"}, result);
    }

    @Test
    public void testGetBeanNamesForType() throws Exception {
        final String[] result = defaultListableBeanFactory.getBeanNamesForType(BaseService.class);
        assertThat(result).isEmpty();
    }

    @Test
    public void testGetBeansOfType() throws Exception {
        final Map<String, BaseService> result = defaultListableBeanFactory.getBeansOfType(BaseService.class);
        for (final Map.Entry<String, BaseService> entry : result.entrySet()) {
            final BaseService baseService = (BaseService)defaultListableBeanFactory.getBean(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println(baseService);
            assertThat(baseService).isEqualTo(entry.getValue());
        }
    }

    @Test
    public void testAddBeanPostProcessor() {
        final AutowiredAnnotationBeanPostProcessor r =  new AutowiredAnnotationBeanPostProcessor();
        defaultListableBeanFactory.addBeanPostProcessor(r);
        assertThat(defaultListableBeanFactory.getBeanPostProcessors().contains(r)).isTrue();
        final int result = defaultListableBeanFactory.getBeanPostProcessorCount();
        Assert.assertEquals(1, result);
    }

    @Test
    public void testApplyBeanPostProcessorsBeforeInitialization() throws Exception {
        defaultListableBeanFactory.registerBean("autowiredService",new AutowiredService());
        final BaseService base = new BaseService();
        final Object result = defaultListableBeanFactory.applyBeanPostProcessorsBeforeInitialization(base, "baseService10");
        Assert.assertEquals(base, result);
    }

    @Test
    public void testApplyBeanPostProcessorsAfterInitialization() throws Exception {
        // applyBeanPostProcessorsAfterInitialization目前是空实现，但是由于不存在BeanPostProcessor，所以会返回入参
        final BaseService baseService = new BaseService();
        final Object result = defaultListableBeanFactory.applyBeanPostProcessorsAfterInitialization(baseService, "baseService10");
        assertThat(result).isEqualTo(baseService);
    }

//    @Test
//    public void testRegisterDependentBean() throws Exception {

//    }

    @Test
    public void testGetDependentBeans() throws Exception {
        final String[] result = defaultListableBeanFactory.getDependentBeans("beanName");
        Assert.assertArrayEquals(new String[]{}, result);
    }

    @Test
    public void testGetDependenciesForBean() throws Exception {
        final String[] result = defaultListableBeanFactory.getDependenciesForBean("beanName");
        Assert.assertArrayEquals(new String[]{}, result);
    }


    @Test
    public void testGetBean() throws Exception {
        final Object result = defaultListableBeanFactory.getBean("baseService1");
        assertThat(result).isNotNull();
    }

    @Test
    public void testContainsBean() throws Exception {
        final boolean result = defaultListableBeanFactory.containsBean("baseService1");
        Assert.assertTrue(result);
    }

    @Test
    public void testIsSingleton() throws Exception {
        final boolean result = defaultListableBeanFactory.isSingleton("baseService1");
        Assert.assertTrue(result);
    }

    @Test
    public void testIsPrototype() throws Exception {
        final boolean result = defaultListableBeanFactory.isPrototype("baseService1");
        Assert.assertFalse(result);
    }

}
