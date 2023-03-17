package com.dianpoint.summer.beans;

/**
 * <p>
 * Bean创建注册和获取的工厂接口
 * </p>
 *
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 11:53
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    /**
     *
     * @param name
     *            要查询的bean的名称
     * @return 是否存在指定名称Bean存在
     */
    boolean containsBean(String name);

    void registerBean(String beanName, Object object);

    boolean isSingleton(String name);

    boolean isPrototype(String name);

    Class<?> getType(String name);
}
