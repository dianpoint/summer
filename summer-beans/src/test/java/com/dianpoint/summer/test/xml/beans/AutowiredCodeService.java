package com.dianpoint.summer.test.xml.beans;


import com.dianpoint.summer.beans.factory.annotation.Autowired;

/**
 * @author wangyi
 * @date 2023/3/26
 */
public class AutowiredCodeService {

    @Autowired
    private AutowiredService autowiredService;


    public AutowiredService getAutowiredService() {
        return this.autowiredService;
    }

    public void setAutowiredService(final AutowiredService autowiredService) {
        this.autowiredService = autowiredService;
    }
}
