package com.dianpoint.summer.beans;

/**
 * <p>Bean的定义</p>
 *
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 11:51
 */
public class BeanDefinition {
    private String id;
    private String className;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
