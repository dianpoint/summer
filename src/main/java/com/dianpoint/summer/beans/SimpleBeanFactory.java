package com.dianpoint.summer.beans;

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

    public SimpleBeanFactory() {}

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = this.getSingleton(beanName);
        if (singleton == null) {
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            // 根据beanDefinition创建得到的单例实例化对象
            singleton = createBean(beanDefinition);
            this.registerBean(beanName, singleton);
            if (beanDefinition.getInitMethodName() != null) {
                // TODO: 2023/3/18 执行BeanDefinition定义中的initMethod方法 先留个口子
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
    private Object createBean(BeanDefinition beanDefinition) throws RuntimeException {
        // 根据BeanDefinition中properType、constructor-arg定义进行创建实例化对象
        Class<?> clazz = null;
        Object object = null;
        Constructor<?> constructor;
        try {
            clazz = Class.forName(beanDefinition.getClassName());

            // 处理构造函数
            ArgumentValues argumentValues = beanDefinition.getConstructorArgumentValues();
            if (!argumentValues.isEmpty()) {
                // 定义构造函数中参数类型、参数值的数组
                Class<?>[] paramTypes = new Class<?>[argumentValues.getArgumentCount()];
                Object[] paramValues = new Object[argumentValues.getArgumentCount()];

                for (int i = 0; i < argumentValues.getArgumentCount(); i++) {
                    ArgumentValue argumentValue = argumentValues.getIndexedArgumentValue(i);
                    // 根据参数数据类型进行逐个处理
                    if ("String".equals(argumentValue.getType())
                        || "java.lang.String".equals(argumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    } else if ("Integer".equals(argumentValue.getType())
                        || "java.lang.Integer".equals(argumentValue.getType())) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] = Integer.valueOf((String)argumentValue.getValue());
                    } else if ("int".equals(argumentValue.getType())) {
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String)argumentValue.getValue());
                    } else {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
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

        // 处理property
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (!propertyValues.isEmpty()) {
            for (int i = 0; i < propertyValues.size(); i++) {
                PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
                String pName = propertyValue.getName();
                Object pValue = propertyValue.getValue();
                String pType = propertyValue.getType();

                Class<?>[] paramTypes = new Class<?>[1];
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
                Object[] paramValues = new Object[1];
                paramValues[0] = pValue;

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

        return object;
    }

}
