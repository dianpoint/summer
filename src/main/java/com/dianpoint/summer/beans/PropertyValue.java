package com.dianpoint.summer.beans;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 17:00
 */
public class PropertyValue {

    private final String type;
    private final String name;
    private final Object value;

    public PropertyValue(String type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
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
}
