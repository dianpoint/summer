package com.dianpoint.summer.test.xml;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.context.ClassPathXmlApplicationContext;
import com.dianpoint.summer.test.utils.JacksonUtils;
import com.dianpoint.summer.test.xml.beans.CircularDependencyEndService;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author wangyi
 * @date 2023/3/25
 */
public class BeansDependencyTest {

    static ClassPathXmlApplicationContext applicationContext;

    @BeforeClass
    public static void init(){
         applicationContext = new ClassPathXmlApplicationContext("xml/beanDependency.xml");
    }


    @Test
    public void circularDependency_commonCase() throws BeansException {
        final CircularDependencyEndService circularDependencyEndService = (CircularDependencyEndService)applicationContext.getBean("circularDependencyEndService");
        // jackson会报错，com.fasterxml.jackson.databind.JsonMappingException: Infinite recursion
        System.out.printf("详情为：%s",JacksonUtils.toJson(circularDependencyEndService));
        assertThat(circularDependencyEndService.getCircularDependencyStartService().getRemark()).isInstanceOf(String.class)
                .isEqualTo(circularDependencyEndService.getCircularDependencyStartService().getCircularDependencyEndService().getCircularDependencyStartService().getRemark());
    }

}
