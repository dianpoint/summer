package com.dianpoint.summer.beans.factory.support;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dianpoint.summer.beans.BeansException;
import com.dianpoint.summer.beans.PropertyValue;
import com.dianpoint.summer.beans.PropertyValues;
import com.dianpoint.summer.beans.factory.BeanFactoryAware;
import com.dianpoint.summer.beans.factory.config.BeanDefinition;
import com.dianpoint.summer.beans.factory.config.ConfigurableBeanFactory;
import com.dianpoint.summer.beans.factory.config.ConstructorArgumentValue;
import com.dianpoint.summer.beans.factory.config.ConstructorArgumentValues;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 14:35
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
    implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    protected Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);

    protected List<String> beanDefinitionNames = new ArrayList<>();

    private Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(16);

    public AbstractBeanFactory() {}

    public void refresh() {
        for (String beanName : beanDefinitionNames) {
            try {
                BeanDefinition bd = beanDefinitions.get(beanName);
                if (!bd.isPrototype()) {
                    getBean(beanName);
                }
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = this.getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }

        BeanDefinition beanDefinition = beanDefinitions.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }

        if (beanDefinition.isSingleton()) {
            singleton = this.earlySingletonObjects.get(beanName);
            if (singleton == null) {
                singleton = createBean(beanDefinition);
                this.registerBean(beanName, singleton);
                applyBeanPostProcessorsBeforeInitialization(singleton, beanName);
                invokeInitMethods(beanDefinition, singleton);
                singleton = applyBeanPostProcessorsAfterInitialization(singleton, beanName);
            }
            if (singleton == null) {
                throw new BeansException("bean is null.");
            }
            return singleton;
        }

        Object prototype = createBean(beanDefinition);
        applyBeanPostProcessorsBeforeInitialization(prototype, beanName);
        invokeInitMethods(beanDefinition, prototype);
        prototype = applyBeanPostProcessorsAfterInitialization(prototype, beanName);
        if (prototype == null) {
            throw new BeansException("bean is null.");
        }
        return prototype;
    }

    private void invokeInitMethods(BeanDefinition beanDefinition, Object singleton) {
        if (singleton instanceof com.dianpoint.summer.beans.factory.InitializingBean) {
            try {
                ((com.dianpoint.summer.beans.factory.InitializingBean) singleton).afterPropertiesSet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (beanDefinition.getInitMethodName() != null) {
            Class<?> clazz = singleton.getClass();
            Method method = null;
            try {
                method = clazz.getMethod(beanDefinition.getInitMethodName());
                method.invoke(singleton);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName);

    public abstract Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName);

    public void destroySingletons() {
        String[] singletonNames = getSingletonNames();
        for (String beanName : singletonNames) {
            Object singleton = getSingleton(beanName);
            if (singleton instanceof com.dianpoint.summer.beans.factory.DisposableBean) {
                try {
                    ((com.dianpoint.summer.beans.factory.DisposableBean) singleton).destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            BeanDefinition bd = beanDefinitions.get(beanName);
            if (bd != null && bd.getDestroyMethodName() != null) {
                invokeDestroyMethod(singleton, bd.getDestroyMethodName());
            }
        }
    }

    private void invokeDestroyMethod(Object bean, String methodName) {
        try {
            Method method = bean.getClass().getMethod(methodName);
            method.invoke(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            return Class.forName(this.beanDefinitions.get(name).getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
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

        if (object instanceof BeanFactoryAware) {
            ((BeanFactoryAware) object).setBeanFactory(this);
        }
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
                    ConstructorArgumentValue constructorArgumentValue =
                        constructorArgumentValues.getIndexedArgumentValue(i);
                    // 根据参数数据类型进行逐个处理
                    if ("String".equals(constructorArgumentValue.getType())
                        || "java.lang.String".equals(constructorArgumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = constructorArgumentValue.getValue();
                    } else if ("Integer".equals(constructorArgumentValue.getType())
                        || "java.lang.Integer".equals(constructorArgumentValue.getType())) {
                        paramTypes[i] = Integer.class;
                        paramValues[i] = Integer.valueOf((String)constructorArgumentValue.getValue());
                    } else if ("int".equals(constructorArgumentValue.getType())) {
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String)constructorArgumentValue.getValue());
                    } else if ("long".equals(constructorArgumentValue.getType())
                        || "java.lang.Long".equals(constructorArgumentValue.getType())) {
                        paramTypes[i] = Long.class;
                        paramValues[i] = Long.valueOf((String)constructorArgumentValue.getValue());
                    } else if ("Long".equals(constructorArgumentValue.getType())) {
                        paramTypes[i] = long.class;
                        paramValues[i] = Long.valueOf((String)constructorArgumentValue.getValue());
                    } else if ("boolean".equals(constructorArgumentValue.getType())
                        || "java.lang.Boolean".equals(constructorArgumentValue.getType())) {
                        paramTypes[i] = Boolean.class;
                        paramValues[i] = Boolean.valueOf((String)constructorArgumentValue.getValue());
                    } else if ("Boolean".equals(constructorArgumentValue.getType())) {
                        paramTypes[i] = boolean.class;
                        paramValues[i] = Boolean.valueOf((String)constructorArgumentValue.getValue());
                    } else if ("double".equals(constructorArgumentValue.getType())
                        || "java.lang.Double".equals(constructorArgumentValue.getType())) {
                        paramTypes[i] = Double.class;
                        paramValues[i] = Double.valueOf((String)constructorArgumentValue.getValue());
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
                    } else if ("Integer".equals(pType) || "java.lang.Integer".equals(pType)) {
                        paramTypes[0] = Integer.class;
                        pValue =  Integer.valueOf(pValue+"");
                    } else if ("int".equals(pType)) {
                        paramTypes[0] = int.class;
                        pValue = Integer.valueOf(pValue+"");
                    } else if ("long".equals(pType)
                        || "java.lang.Long".equals(pType)) {
                        paramTypes[0] = Long.class;
                        pValue = Long.valueOf(pValue+"");
                    } else if ("Long".equals(pType)) {
                        paramTypes[0] = long.class;
                        pValue = Long.valueOf(pValue+"");
                    } else if ("boolean".equals(pType)
                        || "java.lang.Boolean".equals(pType)) {
                        paramTypes[0] = Boolean.class;
                        pValue = Boolean.valueOf(pValue+"");
                    } else if ("Boolean".equals(pType)) {
                        paramTypes[0] = boolean.class;
                        pValue = Boolean.valueOf(pValue+"");
                    } else if ("double".equals(pType)
                        || "java.lang.Double".equals(pType)) {
                        paramTypes[0] = Double.class;
                        pValue = Double.valueOf(pValue+"");
                    } else {
                        paramTypes[0] = String.class;
                    }
                    paramValues[0] = pValue;
                } else {
                    try {
                        // 处理ref属性时，支持短类型名
                        if ("String".equals(pType) || "java.lang.String".equals(pType)) {
                            paramTypes[0] = String.class;
                        } else if ("Integer".equals(pType) || "java.lang.Integer".equals(pType)) {
                            paramTypes[0] = Integer.class;
                        } else if ("int".equals(pType)) {
                            paramTypes[0] = int.class;
                        } else {
                            paramTypes[0] = Class.forName(pType);
                        }
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
