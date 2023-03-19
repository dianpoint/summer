package com.dianpoint.summer.test.service;

public class OtherTwoService {

    private SimpleService simpleService;

    public void sayHello() {
        System.out.printf("%s: Other two Service%n", getClass().getName());
    }

    public void sayBye() {
        simpleService.sayBye();
    }

    public SimpleService getSimpleService() {
        return simpleService;
    }

    public void setSimpleService(SimpleService simpleService) {
        this.simpleService = simpleService;
    }
}
