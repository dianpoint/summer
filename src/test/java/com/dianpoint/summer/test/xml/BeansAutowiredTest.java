package com.dianpoint.summer.test.xml;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.dianpoint.summer.beans.factory.support.DefaultListableBeanFactory;
import com.dianpoint.summer.context.ClassPathXmlApplicationContext;
import com.dianpoint.summer.test.service.SimpleService;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author wangyi
 * @date 2023/3/26
 */
public class BeansAutowiredTest {

    static ClassPathXmlApplicationContext applicationContext;

    @BeforeClass
    public static void init(){
        applicationContext = new ClassPathXmlApplicationContext("xml/beanAutowire.xml");
    }


    @Test
    public void autowiredBean_commonCase() throws BeansException {
        final SimpleService simpleService = (SimpleService)applicationContext.getBean("simpleService");
        simpleService.sayHello();
        assertThat(simpleService).isInstanceOf(SimpleService.class);
    }


//    @Test
//    public void autowired_commonCase(){
//        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
//        processor.setBeanFactory(new DefaultListableBeanFactory());
//
//        processor.postProcessBeforeInitialization()
//
//    }

}
