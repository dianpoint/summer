package com.dianpoint.summer.test.service;

public class OtherTwoService {

    private SimpleService simpleService;

    void sayHello() {
        System.out.println("Other two Service");
    }

    void sayBye(){
        simpleService.sayBye();
    }

    public SimpleService getSimpleService() {
        return simpleService;
    }

    public void setSimpleService(SimpleService simpleService) {
        this.simpleService = simpleService;
    }
}
