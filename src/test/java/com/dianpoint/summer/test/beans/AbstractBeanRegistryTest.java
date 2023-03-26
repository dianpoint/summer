package com.dianpoint.summer.test.beans;

import com.dianpoint.summer.beans.SingletonBeanRegistry;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author wangyi
 * @date 2023/3/26
 */
public abstract class AbstractBeanRegistryTest {

    @Before
    public void init(){
        final SingletonBeanRegistry beanRegistry = this.getSingletonBeanRegistry();
        beanRegistry.registerSingleton("beanRegistryTest1", "beanRegistryTest1");
        beanRegistry.registerSingleton("beanRegistryTest", "测试1");
        beanRegistry.registerSingleton("beanRegistryTest2", "beanRegistryTest2");
        beanRegistry.registerSingleton("beanRegistryTest3", "beanRegistryTest3");
    }


    protected abstract SingletonBeanRegistry getSingletonBeanRegistry();


    @Test
    public void containNames_commonCase(){
        final SingletonBeanRegistry beanRegistry = this.getSingletonBeanRegistry();
        for (final String singletonName : beanRegistry.getSingletonNames()) {
            final boolean result = beanRegistry.containsSingleton(singletonName);
            assertThat(result).isTrue();
        }
    }



}
