package com.dianpoint.summer.test;

import com.dianpoint.summer.beans.NoSuchBeanDefinitionException;
import com.dianpoint.summer.context.ClassPathXmlApplicationContext;
import com.dianpoint.summer.test.service.SimpleService;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 12:54
 */
public class SimpleIOCTest {


    public static void main(String[] args) throws NoSuchBeanDefinitionException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        SimpleService simpleService = (SimpleService)applicationContext.getBean("simpleService");
        simpleService.sayHello();
    }
}
