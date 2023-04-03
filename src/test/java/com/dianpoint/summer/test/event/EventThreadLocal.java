package com.dianpoint.summer.test.event;

/**
 * @author wangyi
 * @date 2023/3/30
 */
public class EventThreadLocal {
    static ThreadLocal<String> tl = new ThreadLocal<>();

    public static String getValue() {
        return tl.get();
    }

    public static void addValue(final String value) {
        tl.set(value);
    }
}
