package com.dianpoint.summer.beans;

import com.dianpoint.summer.core.Resource;
import org.dom4j.Element;

/**
 * <p>
 * ClassPathXmlResource类中已经将XML读取为Element元素,通过Reader即可将Resource转化成BeanDefinition
 * </p>
 * 
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 14:25
 */
public class XmlBeanDefinitionReader {

    private SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    /**
     * 从指定的XML文件加载bean定义
     * 
     * @param resource
     *            XML文件的资源描述
     */
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element)resource.next();
            String id = element.attributeValue("id");
            String className = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(id, className);
            this.simpleBeanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
