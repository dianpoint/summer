package com.dianpoint.summer.test.xml;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.context.ClassPathXmlApplicationContext;
import com.dianpoint.summer.test.xml.beans.PropertiesTestBean;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author wangyi
 * @date 2023/3/25
 */
public class BeansElementTest {

    static ClassPathXmlApplicationContext applicationContext;

    @BeforeClass
    public static void init(){
         applicationContext = new ClassPathXmlApplicationContext("xml/beanElement.xml");
    }


    @Test
    public void getBean_commonCase() throws BeansException {
        final Object theFool = applicationContext.getBean("theFool");

        assertThat(theFool).isInstanceOf(String.class).isEqualTo("theFool");
    }

    @Test
    public void getBean_UppercaseCase() throws BeansException {
        // 严格beanName区分大小写,所以"THEFOOL"会报空指针
        assertThatThrownBy(()->applicationContext.getBean("THEFOOL")).isInstanceOf(NullPointerException.class);
    }


    @Test
    public void getBean_propertiesCommonCase() throws BeansException {
        final PropertiesTestBean theFool = (PropertiesTestBean)applicationContext.getBean("propertiesBean");
        assertThat(theFool.getAge()).isEqualTo(30);
        assertThat(theFool.getPassword()).isBlank();
        assertThat(theFool.getUsername()).isEqualTo("propertiesBean");
        assertThat(theFool.getSex()).isZero();
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
