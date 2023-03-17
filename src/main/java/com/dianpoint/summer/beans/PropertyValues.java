package com.dianpoint.summer.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 17:00
 */
public class PropertyValues {

    private List<PropertyValue> propertyValueList;

    public PropertyValues(List<PropertyValue> propertyValueList) {
        this.propertyValueList = new ArrayList<>(0);
    }
}
