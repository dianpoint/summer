package com.dianpoint.summer.beans.factory.xml;

import com.dianpoint.summer.beans.*;
import com.dianpoint.summer.beans.factory.config.BeanDefinition;
import com.dianpoint.summer.beans.factory.config.ConstructorArgumentValue;
import com.dianpoint.summer.beans.factory.config.ConstructorArgumentValues;
import com.dianpoint.summer.beans.factory.support.SimpleBeanFactory;
import com.dianpoint.summer.core.Resource;
import org.dom4j.Element;

import java.util.ArrayList;
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

            // 处理constructor-arg节点
            List<Element> constructorElements = element.elements("constructor-arg");
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            for (Element constructorElement : constructorElements) {
                String constructorType = constructorElement.attributeValue("type");
                String constructorName = constructorElement.attributeValue("name");
                String constructorValue = constructorElement.attributeValue("value");

                constructorArgumentValues.addArgumentValues(new ConstructorArgumentValue(constructorType, constructorName, constructorValue));
            }
            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

            // 解析properties中属性
            List<Element> propertyElements = element.elements("property");
            PropertyValues propertyValues = new PropertyValues();

            List<String> refs = new ArrayList<>();
            for (Element propertyElement : propertyElements) {
                // 获取Property节点
                String propertyType = propertyElement.attributeValue("type");
                String propertyName = propertyElement.attributeValue("name");
                String propertyValue = propertyElement.attributeValue("value");
                String propertyRef = propertyElement.attributeValue("ref");
                String refValue = "";
                boolean isRef = false;
                // 解析property标签后 获取ref节点参数 并且根据是否存在存在ref参数来设置PropertyValue,通过isRef标记一个bean是否引用了另外的bean
                if (propertyValue != null && !propertyValue.equals("")) {
                    refValue = propertyValue;
                } else if (propertyRef != null && !propertyRef.equals("")) {
                    isRef = true;
                    refValue = propertyRef;
                    refs.add(refValue);
                }
                // 放入PropertyValues中
                propertyValues.addPropertyValue(new PropertyValue(propertyType, propertyName, refValue, isRef));
            }
            // 将PropertyValues放入BeanDefinition中 以便后续进行实例化
            beanDefinition.setPropertyValues(propertyValues);
            beanDefinition.setDependsOn(refs.toArray(new String[0]));

            // 正式注册BeanDefinition
            this.simpleBeanFactory.registerBeanDefinition(id, beanDefinition);
        }
    }
}
