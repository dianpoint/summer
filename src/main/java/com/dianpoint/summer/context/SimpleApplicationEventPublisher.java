package com.dianpoint.summer.context;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/24 14:33
 */
public class SimpleApplicationEventPublisher implements ApplicationEventPublisher {

    private List<ApplicationListener> listeners = new ArrayList<>();

    @Override
    public void publisher(ApplicationEvent event) {
        for (ApplicationListener listener : listeners) {
            listener.onApplicationEvent(event);
        }
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.listeners.add(listener);
    }
}
