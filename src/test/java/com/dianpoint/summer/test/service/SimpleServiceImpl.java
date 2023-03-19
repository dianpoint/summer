package com.dianpoint.summer.test.service;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 12:55
 */
public class SimpleServiceImpl implements SimpleService {

    private String propertyName;

    private String name;
    private int age;

    private OtherOneService otherOneService;

    public SimpleServiceImpl(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void sayHello() {
        System.out.println("hello: " + propertyName);
        // 打印通过构造器方式注入的属性name 和 age
        System.out.printf("name:%s,age:%s%n", name, age);
        System.out.println("================");

        otherOneService.sayHello();
    }

    @Override
    public void sayBye() {
        System.out.println("SimpleServiceImpl bye");
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public OtherOneService getOtherOneService() {
        return otherOneService;
    }

    public void setOtherOneService(OtherOneService otherOneService) {
        this.otherOneService = otherOneService;
    }
}
