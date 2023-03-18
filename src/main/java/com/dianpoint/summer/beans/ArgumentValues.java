package com.dianpoint.summer.beans;

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
public class ArgumentValues {

    private List<ArgumentValue> argumentValues = new LinkedList<>();

    public ArgumentValues() {}

    /**
     * 根据Index索引顺序获取参数
     * 
     * @param index
     *            参数索引位置
     * @return 返回参数类型
     */
    public ArgumentValue getIndexedArgumentValue(int index) {
        return this.argumentValues.get(index);
    }

    public int getArgumentCount() {
        return this.argumentValues.size();
    }

    public boolean isEmpty() {
        return this.argumentValues.isEmpty();
    }

    public void addArgumentValues(ArgumentValue argumentValue) {
        this.argumentValues.add(argumentValue);
    }
}
