package com.dianpoint.summer.test;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.context.ClassPathXmlApplicationContext;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 12:54
 */
public class SimpleIOCTest {

    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");


    }
}
