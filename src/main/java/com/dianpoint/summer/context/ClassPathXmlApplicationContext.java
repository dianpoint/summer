package com.dianpoint.summer.context;

import com.dianpoint.summer.beans.BeanDefinition;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 11:59
 */
public class ClassPathXmlApplicationContext {

    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private final Map<String, Object> singletons = new HashMap<>();

    /**
     * 构造函数读取fileName中外部xml配置,将Bean的定义加载进入内存，并且初始化给BeanDefinition
     *
     * @param fileName
     *            外部xml配置文件
     */
    public ClassPathXmlApplicationContext(String fileName) {
        this.readXml(fileName);
        this.instanceBeans();
    }

    /**
     * 读取fileName对应xml配置文件。
     *
     * @param fileName
     *            资源文件地址
     */
    private void readXml(String fileName) {
        SAXReader saxReader = new SAXReader();
        try {
            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();
            // 遍历xml配置文件,读取每一个`<bean></bean>`配置
            for (Element element : rootElement.elements()) {
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                // 初始化创建BeanDefinition
                BeanDefinition beanDefinition = new BeanDefinition(id, className);
                // 存入BeanDefinitions中
                beanDefinitions.add(beanDefinition);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用反射创建BeanDefinition,并将其存储singletons中
     */
    private void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                singletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对外暴露获取Bean方法,从容器中获取Bean
     *
     * @param beanName
     *            beanName
     * @return beanName对应的class实例
     */
    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }
}
