package com.dianpoint.summer.test.xml;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.dianpoint.summer.context.ClassPathXmlApplicationContext;
import com.dianpoint.summer.test.xml.beans.AutowiredCodeService;
import com.dianpoint.summer.test.xml.beans.AutowiredService;
import com.dianpoint.summer.test.xml.beans.BaseService;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        final BaseService baseService = (BaseService)applicationContext.getBean("baseService");
        assertThat(baseService).isInstanceOf(BaseService.class);
    }

    @Test
    public void autowiredBean_baseServiceCase() throws BeansException {
        assertThatThrownBy(()->applicationContext.getBean("baseService1")).isInstanceOf(NullPointerException.class);

    }


    @Test
    public void autowired_commonCase() throws BeansException {
        AutowiredCodeService autowiredCodeService = new AutowiredCodeService();
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setBeanFactory(applicationContext.getBeanFactory());

        // 判断通过@autowired注入的类和方法设置的类是否一致
        final AutowiredService autowiredService = (AutowiredService)applicationContext.getBean("autowiredService");

        processor.postProcessBeforeInitialization(autowiredCodeService,"");
        System.out.println(autowiredService);
        System.out.println(autowiredCodeService.getAutowiredService());
        assertThat(autowiredService).isEqualTo(autowiredCodeService.getAutowiredService());
    }

}
