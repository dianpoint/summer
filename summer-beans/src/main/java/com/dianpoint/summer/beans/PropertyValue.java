package com.dianpoint.summer.beans;

/**
 * <p>
 * 用以保存BeanDefinition在xml文件中的定义,包括待注入的属性类型type、名称name、值value、引用类型ref
 * </p>
 * 
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 17:00
 */
public class PropertyValue {

    private final String type;
    private final String name;
    private final Object value;

    /**
     * 属性是否是引用属性
     */
    private final boolean isRef;

    public PropertyValue(String type, String name, Object value, boolean isRef) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.isRef = isRef;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public boolean isRef() {
        return isRef;
    }
}
