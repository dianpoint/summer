package com.dianpoint.summer.test.aop.proxy;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/26 21:31
 */
public class SomeServiceImpl implements SomeService {
    @Override
    public void action() {
        System.out.println("代理类的真实执行:SomeServiceImpl really action... ");
    }
}
