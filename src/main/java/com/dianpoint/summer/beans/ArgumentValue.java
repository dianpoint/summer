package com.dianpoint.summer.beans;

/**
 * <p>
 * 定义BeanDefinition在xml文件中的格式,包括value|type|name属性
 * </p>
 * 
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 16:58
 */
public class ArgumentValue {

    private Object value;

    private String name;

    private String type;

    public ArgumentValue(String type, Object value) {
        this.value = value;
        this.type = type;
    }

    public ArgumentValue(String type, String name, Object value) {
        this.value = value;
        this.name = name;
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
