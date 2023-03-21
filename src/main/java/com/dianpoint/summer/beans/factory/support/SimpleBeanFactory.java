package com.dianpoint.summer.beans.factory.support;

import com.dianpoint.summer.beans.*;
import com.dianpoint.summer.beans.factory.config.BeanDefinition;
import com.dianpoint.summer.beans.factory.config.ConstructorArgumentValue;
import com.dianpoint.summer.beans.factory.config.ConstructorArgumentValues;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 14:35
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);

    private List<String> beanDefinitionNames = new ArrayList<>();

    private Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(16);

    public SimpleBeanFactory() {}

    /**
     * 对于refresh方法进行刷新Bean处理。本质上是对于所有的beanName分别进行getBean处理
     */
    public void refresh() {
        for (String beanName : beanDefinitionNames) {
            try {
                getBean(beanName);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = this.getSingleton(beanName);
        if (singleton == null) {
            // 当前没有单例对象时 进行获取一个早期的bean定义
            singleton = this.earlySingletonObjects.get(beanName);
            if (singleton == null) {
                BeanDefinition beanDefinition = beanDefinitions.get(beanName);
                // 根据beanDefinition创建得到的单例实例化对象
                singleton = createBean(beanDefinition);
                this.registerBean(beanName, singleton);
                // beanPostProcessor方法进行执行
                // postProcessBeforeInitialization
                // afterPropertiesSet
                // initMethod
                // postProcessAfterInitialization
            }
        }
        // 二次判断如果未实例化成功则异常处理
        if (singleton == null) {
            throw new BeansException("bean is null.");
        }
        return singleton;
    }

    @Override
    public boolean containsBean(String name) {
        return this.containsSingleton(name);
    }

    @Override
    public void registerBean(String beanName, Object object) {
        this.registerSingleton(beanName, object);
    }

    @Override
    public boolean isSingleton(String name) {
        return this.beanDefinitions.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return this.beanDefinitions.get(name).isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanDefinitions.get(name).getClass();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitions.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
        if (!beanDefinition.isLazyInit()) {
            try {
                // 若定义BeanDefinition为非懒加载时 即时注册
                getBean(name);
            } catch (BeansException ignored) {
            }
        }
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitions.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitions.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionNames.contains(name);
    }

    /**
     * 根据BeanDefinition定义创建Bean示例过程
     *
     * @param beanDefinition
     *            BeanDefinition定义
     * @return 实例化对象
     */
    private Object createBean(BeanDefinition beanDefinition) {
        Class<?> clazz = null;
        Object object = doCreateBean(beanDefinition);
        // 将创建好的早起毛坯Bean放入earlySingletonObjects中,此时暂未处理ref
        this.earlySingletonObjects.put(beanDefinition.getId(), object);

        try {
            clazz = Class.forName(beanDefinition.getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 处理ref属性
        handleProperties(beanDefinition, clazz, object);
        return object;
    }

    private Object doCreateBean(BeanDefinition beanDefinition) {
        // 根据BeanDefinition中properType、constructor-arg定义进行创建实例化对象
        Class<?> clazz;
        Object object = null;
        Constructor<?> constructor;
        try {
            clazz = Class.forName(beanDefinition.getClassName());

            // 处理构造函数
            ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
            if (!constructorArgumentValues.isEmpty()) {
                // 定义构造函数中参数类型、参数值的数组
                Class<?>[] paramTypes = new Class<?>[constructorArgumentValues.getArgumentCount()];
                Object[] paramValues = new Object[constructorArgumentValues.getArgumentCount()];

                for (int i = 0; i < constructorArgumentValues.getArgumentCount(); i++) {
                    ConstructorArgumentValue constructorArgumentValue = constructorArgumentValues.getIndexedArgumentValue(i);
                    // 根据参数数据类型进行逐个处理
                    if ("String".equals(constructorArgumentValue.getType())
                        || "java.lang.String".equals(constructorArgumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = constructorArgumentValue.getValue();
                    } else if ("Integer".equals(constructorArgumentValue.getType())
                        || "java.lang.Integer".equals(constructorArgumentValue.getType())) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] = Integer.valueOf((String) constructorArgumentValue.getValue());
                    } else if ("int".equals(constructorArgumentValue.getType())) {
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String) constructorArgumentValue.getValue());
                    } else {
                        paramTypes[i] = String.class;
                        paramValues[i] = constructorArgumentValue.getValue();
                    }
                }
                constructor = clazz.getConstructor(paramTypes);
                object = constructor.newInstance(paramValues);
            } else {
                object = clazz.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    /**
     * 处理beanDefinition中Property属性
     * 
     * @param beanDefinition
     *            beanDefinition
     * @param clazz
     *            className
     * @param object
     *            object instance
     */
    private void handleProperties(BeanDefinition beanDefinition, Class<?> clazz, Object object) {
        // 处理property 此时就需要区分property节点是属性 还是 引用类型
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (!propertyValues.isEmpty()) {
            for (int i = 0; i < propertyValues.size(); i++) {
                PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
                String pName = propertyValue.getName();
                Object pValue = propertyValue.getValue();
                String pType = propertyValue.getType();
                boolean isRef = propertyValue.isRef();

                Class<?>[] paramTypes = new Class<?>[1];
                Object[] paramValues = new Object[1];
                if (!isRef) {
                    // 普通值类型
                    if ("String".equals(pType) || "java.lang.String".equals(pType)) {
                        paramTypes[0] = String.class;
                    } else if ("Integer".equals(pType) || "java.lang.String".equals(pType)) {
                        paramTypes[0] = Integer.class;
                    } else if ("int".equals(pType)) {
                        paramTypes[0] = int.class;
                    } else {
                        // TODO: 2023/3/18 此处对于数据类型需要逐个处理 此处仅仅建立框架 后续完善 兜底String类型处理
                        paramTypes[0] = String.class;
                    }
                    paramValues[0] = pValue;
                } else {
                    try {
                        paramTypes[0] = Class.forName(pType);
                        // 当前为bean引用类型,再次调用getBean进行创建
                        paramValues[0] = getBean((String)pValue);
                    } catch (ClassNotFoundException | BeansException e) {
                        e.printStackTrace();
                    }
                }

                // 手动构造set方法进行赋值 驼峰书写
                String methodName = "set" + pName.substring(0, 1).toUpperCase() + pName.substring(1);
                try {
                    Method method = clazz.getMethod(methodName, paramTypes);
                    method.invoke(object, paramValues);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
