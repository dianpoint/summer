package com.dianpoint.summer.beans.factory.config;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 参数值封装对象，提供了 ArgumentValues 和 PropertyValues 两个类，封装、 增加、获取、判断等操作方法
 * </p>
 * 
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 16:58
 */
public class ConstructorArgumentValues {

    private List<ConstructorArgumentValue> constructorArgumentValues = new LinkedList<>();

    public ConstructorArgumentValues() {}

    /**
     * 根据Index索引顺序获取参数
     * 
     * @param index
     *            参数索引位置
     * @return 返回参数类型
     */
    public ConstructorArgumentValue getIndexedArgumentValue(int index) {
        return this.constructorArgumentValues.get(index);
    }

    public int getArgumentCount() {
        return this.constructorArgumentValues.size();
    }

    public boolean isEmpty() {
        return this.constructorArgumentValues.isEmpty();
    }

    public void addArgumentValues(ConstructorArgumentValue constructorArgumentValue) {
        this.constructorArgumentValues.add(constructorArgumentValue);
    }
}
