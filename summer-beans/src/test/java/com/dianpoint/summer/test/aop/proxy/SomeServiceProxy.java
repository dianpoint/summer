package com.dianpoint.summer.test.aop.proxy;

import com.dianpoint.summer.aop.ProxyFactoryBean;
import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.beans.factory.BeanFactory;
import com.dianpoint.summer.beans.factory.FactoryBean;
import com.dianpoint.summer.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 21:36
 */
public class SomeServiceProxy {

    @Test
    public void testProxy() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ProxyFactoryBean proxyAction = (ProxyFactoryBean)applicationContext.getBean("proxyAction");
        SomeService someService = (SomeService) proxyAction.getSingletonInstance();
        someService.action();
    }

}
