package com.dianpoint.summer.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 解析Resource文件中Property属性节点,将其批量存储PropertyValues中
 * </p>
 * 
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 17:00
 */
public class PropertyValues {

    private List<PropertyValue> propertyValueList;

    public PropertyValues() {
        this.propertyValueList = new ArrayList<>(0);
    }

    /**
     * 设置propertyValue
     * 
     * @param propertyValue
     *            资源属性值
     */
    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    /**
     * 设置propertyValue
     * 
     * @param propertyType
     *            属性类型
     * @param propertyName
     *            属性名称
     * @param propertyValue
     *            属性值
     */
    public void addPropertyValue(String propertyType, String propertyName, Object propertyValue) {
        addPropertyValue(new PropertyValue(propertyType, propertyName, propertyValue));
    }

    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }

    public int size() {
        return this.propertyValueList.size();
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
