package com.dianpoint.summer.test.service;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 12:55
 */
public class SimpleServiceImpl implements SimpleService {

    private String propertyName;

    @Override
    public void sayHello() {
        System.out.println("hello " + propertyName);
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
