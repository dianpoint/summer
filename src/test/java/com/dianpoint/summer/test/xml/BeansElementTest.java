package com.dianpoint.summer.test.xml;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.context.ClassPathXmlApplicationContext;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author wangyi
 * @date 2023/3/25
 */
public class BeansElementTest {


    @Test
    public void getBean_commonCase() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("xml/beanElement.xml");
        Object theFool = applicationContext.getBean("theFool");

        assertThat(theFool).isInstanceOf(String.class);
    }

    @Test
    public void getBean_UppercaseCase() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("xml/beanElement.xml");
        // 严格beanName区分大小写,所以"THEFOOL"会报空指针
        assertThatThrownBy(()->applicationContext.getBean("THEFOOL")).isInstanceOf(NullPointerException.class);

    }


//  环境相关
//    @Test
//    public void getBean_withEnvironment() throws BeansException {
//        Environment env = new Environment();
//        env.setActiveProfiles("dev");
//
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanElement.xml");
//        Object theFool = applicationContext.getBean("theFool");
//
//        assertThat(theFool).isInstanceOf(Integer.class);
//
//    }
}
