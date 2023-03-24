package com.dianpoint.summer.context;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/24 14:31
 */
public class ContextRefreshEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source
     *            The object on which the Event initially occurred.
     * @throws IllegalArgumentException
     *             if source is null.
     */
    public ContextRefreshEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return this.message;
    }
}
