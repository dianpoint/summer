package com.dianpoint.summer.test.xml.beans;

/**
 * @author wangyi
 * @date 2023/3/25
 */
public class PropertiesTestBean {

    private String username;

    public String password;

    private Integer age;
    // 0/1
    private int sex;

    public String remark;

    public int getSex() {
        return this.sex;
    }

    public void setSex(final int sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }
}
