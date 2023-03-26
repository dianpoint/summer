package com.dianpoint.summer.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 15:12
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private List<String> beanNames = new ArrayList<>();
    private Map<String, Object> singletons = new ConcurrentHashMap<>(256);

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        // 保证BeanNames和singletons的操作的原子性
        synchronized (this.singletons) {
            this.singletons.put(beanName, singletonObject);
            this.beanNames.add(beanName);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletons.get(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return this.singletons.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return this.beanNames.toArray(new String[this.beanNames.size()]);
    }

    protected void removeSingleton(String beanName) {
        synchronized (this.singletons) {
            this.singletons.remove(beanName);
            this.beanNames.remove(beanName);
        }
    }
}
