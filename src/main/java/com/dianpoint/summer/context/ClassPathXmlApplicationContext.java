package com.dianpoint.summer.context;

import com.dianpoint.summer.beans.BeanFactory;
import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.beans.SimpleBeanFactory;
import com.dianpoint.summer.beans.XmlBeanDefinitionReader;
import com.dianpoint.summer.core.ClassPathXmlResource;
import com.dianpoint.summer.core.Resource;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 11:59
 */
public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {
    private SimpleBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName, true);
    }

    /**
     * 
     * <ul>
     * <li>构造函数读取fileName中外部xml配置,将Bean的定义加载进入内存，并且初始化给BeanDefinition</li>
     * <li>context负责整合IOC容器的启动过程，读外部配置，解析Bean定义，创建BeanFactory</li>
     * </ul>
     *
     * @param fileName
     *            外部xml配置文件
     * @param isRefresh
     *            是否刷新Bean
     */
    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) {
        // 加载外部xml文件定义为Resource资源
        Resource resource = new ClassPathXmlResource(fileName);

        SimpleBeanFactory factory = new SimpleBeanFactory();
        // 解析BeanDefinition定义
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        // 创建BeanFactory
        this.beanFactory = factory;
        if (isRefresh) {
            this.beanFactory.refresh();
        }
    }

    /**
     * 对外暴露获取Bean方法,从容器中获取Bean
     *
     * @param beanName
     *            beanName
     * @return beanName对应的class实例
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    @Override
    public void registerBean(String beanName, Object object) {
        this.beanFactory.registerBean(beanName, object);
    }

    @Override
    public boolean isSingleton(String name) {
        return this.beanFactory.isSingleton(name);
    }

    @Override
    public boolean isPrototype(String name) {
        return this.beanFactory.isPrototype(name);
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanFactory.getType(name);
    }

    @Override
    public void publisher(ApplicationEvent event) {

    }
}
