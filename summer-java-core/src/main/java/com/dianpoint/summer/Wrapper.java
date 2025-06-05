package com.dianpoint.summer;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/5 20:42
 */

public interface Wrapper {

    <T> T unwrap(Class<T> type) throws IllegalArgumentException;

    boolean isWrapperFor(Class<?> type);

    static <T> T tryUnwrap(Object object, Class<T> type) {
        if (object instanceof Wrapper) {
            Wrapper wrapper = (Wrapper) object;
            if (wrapper.isWrapperFor(type)) {
                return wrapper.unwrap(type);
            }
        }
        return null;
    }

}
