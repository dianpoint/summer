package com.dianpoint.summer.context;

import java.util.EventObject;

/**
 * @author: congcong
 * @email: congccoder@gmail.com
 * @date: 2023/3/17 16:10
 */
public class ApplicationEvent extends EventObject {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a prototypical Event.
     *
     * @param source
     *            The object on which the Event initially occurred.
     * @throws IllegalArgumentException
     *             if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
