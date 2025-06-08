package com.dianpoint.summer.lang;

import com.dianpoint.summer.utils.AssertUtils;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface ThrowableConsumer<T> {

    void accept(T t) throws Throwable;


    /**
     * 定义一个默认的异常处理方法，用来固定抛出一个runtimeException
     *
     * @param t         入参
     * @param throwable 待抛出的异常
     */
    default void handleException(T t, Throwable throwable) {
        throw new RuntimeException(throwable);
    }

    /**
     * 默认执行逻辑，无客制化异常处理逻辑，采用默认的异常处理方法{@link #handleException(Object, Throwable)}
     *
     * @param t 入参
     */
    default void execute(T t) {
        execute(t, this::handleException);
    }

    default void execute(T t, BiConsumer<T, Throwable> exceptionHandler) throws NullPointerException {
        AssertUtils.assertNotNull(exceptionHandler, () -> "The 'exceptionHandler' must not be null");
        try {
            accept(t);
        } catch (Throwable failure) {
            exceptionHandler.accept(t, failure);
        }
    }

    default void execute(T t, ThrowableConsumer<T> consumer) throws NullPointerException {
        consumer.execute(t, this::handleException);
    }

    default void execute(T t, ThrowableConsumer<T> consumer, BiConsumer<T, Throwable> exceptionHandler)
            throws NullPointerException {
        AssertUtils.assertNotNull(exceptionHandler, () -> "The 'exceptionHandler' must not be null");
        consumer.execute(t, exceptionHandler);
    }
}
