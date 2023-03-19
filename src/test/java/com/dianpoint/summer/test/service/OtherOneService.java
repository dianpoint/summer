package com.dianpoint.summer.test.service;

public class OtherOneService {

    private OtherTwoService otherTwoService;

    public void sayHello() {
        System.out.println("Other One Service");
        otherTwoService.sayHello();
    }

    public OtherTwoService getOtherTwoService() {
        return otherTwoService;
    }

    public void setOtherTwoService(OtherTwoService otherTwoService) {
        this.otherTwoService = otherTwoService;
    }
}
