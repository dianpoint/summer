package com.dianpoint.summer.beans;

import com.dianpoint.summer.core.Resource;
import org.dom4j.Element;

import java.util.List;

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
     * 从指定的XML文件加载beanDefinition定义,将其以ArgumentValue为单元映射到BeanDefinition当中
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

            // 解析xml文件中property节点 将其映射到PropertyValues中
            List<Element> propertyElements = element.elements("property");
            PropertyValues propertyValues = new PropertyValues();
            for (Element propertyElement : propertyElements) {
                // 获取Property节点
                String propertyType = propertyElement.attributeValue("type");
                String propertyName = propertyElement.attributeValue("name");
                String propertyValue = propertyElement.attributeValue("value");
                // 放入PropertyValues中
                propertyValues.addPropertyValue(new PropertyValue(propertyType, propertyName, propertyValue));
            }
            // 将PropertyValues放入BeanDefinition中 以便后续进行实例化
            beanDefinition.setPropertyValues(propertyValues);

            // 获取constructor-arg节点
            List<Element> constructorElements = element.elements("constructor-arg");
            ArgumentValues argumentValues = new ArgumentValues();
            for (Element constructorElement : constructorElements) {
                String constructorType = constructorElement.attributeValue("type");
                String constructorName = constructorElement.attributeValue("name");
                String constructorValue = constructorElement.attributeValue("value");

                argumentValues.addArgumentValues(new ArgumentValue(constructorType, constructorName, constructorValue));
            }
            beanDefinition.setConstructorArgumentValues(argumentValues);
            // 正式注册BeanDefinition
            this.simpleBeanFactory.registerBeanDefinition(id, beanDefinition);
        }
    }
}
