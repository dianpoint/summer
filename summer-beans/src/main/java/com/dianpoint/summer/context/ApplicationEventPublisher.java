package com.dianpoint.summer.context;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 16:11
 */
public interface ApplicationEventPublisher {

    void publisher(ApplicationEvent event);

    void addApplicationListener(ApplicationListener listener);
}
