package com.dianpoint.summer.test.service;

public class ActionOne implements IAction {
    @Override
    public void doAction() {
        System.out.println("执行真是的doAction");
    }

    @Override
    public void doSomething() {
        System.out.println("执行真实的doSomething");
    }
}
