package com.dianpoint.summer.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    private Map<Integer, ArgumentValue> indexedArgumentValues = new HashMap<>(0);
    private List<ArgumentValue> genericArgumentValues = new LinkedList<>();

    public ArgumentValues() {}

    public void addArgumentValue(Integer index, ArgumentValue newValue) {
        this.indexedArgumentValues.put(index, newValue);
    }

    public void addGenericArgumentValue(ArgumentValue newValue) {
        if (newValue.getName() != null) {
            Iterator<ArgumentValue> iterator = this.genericArgumentValues.iterator();
            while (iterator.hasNext()) {
                ArgumentValue currentValue = iterator.next();
                if (newValue.getName().equals(currentValue.getName())) {
                    iterator.remove();
                }
            }
        }
        this.genericArgumentValues.add(newValue);
    }

    public void addGenericArgumentValue(Object value, String type) {
        this.genericArgumentValues.add(new ArgumentValue(value, type));
    }

    public ArgumentValue getGenericArgumentValue(String requireName) {
        for (ArgumentValue valueItem : this.genericArgumentValues) {
            // 返回requireName对应的ArgumentValue
            if (valueItem.getValue() != null && (requireName == null || !requireName.equals(valueItem.getName()))) {
                continue;
            }
            return valueItem;
        }
        return null;
    }

    public boolean hasIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.containsKey(index);
    }

    public ArgumentValue getIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.get(index);
    }

    public int getArgumentCount() {
        return this.genericArgumentValues.size();
    }

    public boolean isEmpty() {
        return this.genericArgumentValues.isEmpty();
    }
}
