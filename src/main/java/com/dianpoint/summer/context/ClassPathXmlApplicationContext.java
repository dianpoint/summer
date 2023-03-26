package com.dianpoint.summer.context;

import java.util.ArrayList;
import java.util.List;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.dianpoint.summer.beans.factory.config.BeanFactoryPostProcessor;
import com.dianpoint.summer.beans.factory.config.ConfigurableListableBeanFactory;
import com.dianpoint.summer.beans.factory.support.DefaultListableBeanFactory;
import com.dianpoint.summer.beans.factory.xml.XmlBeanDefinitionReader;
import com.dianpoint.summer.core.ClassPathXmlResource;
import com.dianpoint.summer.core.Resource;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 11:59
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;

    private List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();

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

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 解析BeanDefinition定义
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        // 创建BeanFactory
        this.beanFactory = factory;
        if (isRefresh) {
            try {
                refresh();
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return this.beanFactory;
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
        this.getApplicationEventPublisher().publisher(event);
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        getApplicationEventPublisher().addApplicationListener(listener);
    }

    @Override
    public void finishRefresh() {
        publisher(new ContextRefreshEvent("Application context refreshed finish"));
    }

    @Override
    public void registerListeners() {
        ApplicationListener applicationListener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(applicationListener);
    }

    @Override
    public void initApplicationEventPublisher() {
        ApplicationEventPublisher applicationEventPublisher = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(applicationEventPublisher);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) {

    }

    @Override
    public void registerBeanPostProcessors(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    @Override
    public void onRefresh() {
        this.beanFactory.refresh();
    }

}
