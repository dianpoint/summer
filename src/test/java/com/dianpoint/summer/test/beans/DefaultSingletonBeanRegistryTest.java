package com.dianpoint.summer.test.beans;

import com.dianpoint.summer.beans.DefaultSingletonBeanRegistry;
import com.dianpoint.summer.beans.SingletonBeanRegistry;

/**
 * DefaultSingletonBeanRegistry对应的测试用例
 * @author wangyi
 * @date 2023/3/26
 */
public class DefaultSingletonBeanRegistryTest extends AbstractBeanRegistryTest {

    DefaultSingletonBeanRegistry defaultSingletonBeanRegistry = new DefaultSingletonBeanRegistry();


    @Override
    protected SingletonBeanRegistry getSingletonBeanRegistry() {
        return defaultSingletonBeanRegistry;
    }



}
