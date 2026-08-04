package com.dianpoint.summer.test.aop.proxy;

import com.dianpoint.summer.aop.ProxyFactoryBean;
import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.context.ClassPathXmlApplicationContext;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SomeServiceProxyTest {

    @Test
    public void testProxy() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ProxyFactoryBean proxyAction = (ProxyFactoryBean) applicationContext.getBean("proxyAction");
        SomeService someService = (SomeService) proxyAction.getSingletonInstance();

        assertThat(someService).isNotNull();
        assertThat(someService).isInstanceOf(SomeService.class);
        assertThat(proxyAction.getTarget()).isNotNull();
        assertThat(proxyAction.getTarget()).isInstanceOf(SomeService.class);
    }

}
