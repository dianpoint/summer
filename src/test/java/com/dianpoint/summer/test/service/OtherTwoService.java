package com.dianpoint.summer.test.service;

public class OtherTwoService {

    private SimpleService simpleService;

    void sayHello() {
        System.out.println("Other two Service");
    }

    void sayBye(){
        simpleService.sayBye();
    }
}
