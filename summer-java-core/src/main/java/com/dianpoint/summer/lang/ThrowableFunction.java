package com.dianpoint.summer.lang;

import com.dianpoint.summer.utils.AssertUtils;

import java.util.function.BiFunction;

/**
 * 一个通用的抽象的处理异常的function
 *
 * @param <T> 入参
 * @param <R> 结果
 */
@FunctionalInterface
public interface ThrowableFunction<T, R> {

    R apply(T t) throws Throwable;

    default R handlerException(T t, Throwable throwable) {
        throw new RuntimeException(throwable);
    }

    default R execute(T t, BiFunction<T, Throwable, R> exceptionHandler) {
        R result;
        try {
            result = apply(t);
        } catch (Throwable failure) {
            result = exceptionHandler.apply(t, failure);
        }
        return result;
    }

    default R execute(T t) throws RuntimeException {
        return execute(t, this::handlerException);
    }

    default <T, R> R execute(T t, ThrowableFunction<T, R> function, BiFunction<T, Throwable, R> exceptionHandler)
            throws IllegalArgumentException {
        AssertUtils.assertNotNull(function, "The 'function' must not be null");
        AssertUtils.assertNotNull(exceptionHandler, "The 'exceptionHandler' must not be null");

        return function.execute(t, exceptionHandler);
    }

    default <T, R> R execute(T t, ThrowableFunction<T, R> function) throws IllegalArgumentException {
        AssertUtils.assertNotNull(function, "The 'function' must not be null");
        return execute(t, function, function::handlerException);
    }

    default <V> ThrowableFunction<V, R> compose(ThrowableFunction<? super V, ? extends T> before) {
        AssertUtils.assertNotNull(before, "The 'before' must not be null");
        return (V v) -> apply(before.apply(v));
    }

    default <V> ThrowableFunction<T, V> andThen(ThrowableFunction<? super R, ? extends V> after) {
        AssertUtils.assertNotNull(after, "The 'after' must not be null");
        return (T t) -> after.apply(apply(t));
    }


}
