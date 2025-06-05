package com.dianpoint.summer.lang;

import java.util.function.Function;

import static com.dianpoint.summer.utils.AssertUtils.assertNotNull;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/5 20:44
 */

@FunctionalInterface
public interface ThrowableSupplier<T> {

    T get() throws Throwable;

    default T execute() {
        return execute(this::handleException);
    }

    default T handleException(Throwable failure) {
        throw new RuntimeException(failure);
    }

    default T execute(Function<Throwable, T> exceptionHandler) {
        assertNotNull(exceptionHandler, () -> "The 'exceptionHandler' must not be null");
        T result;
        try {
            result = get();
        } catch (Throwable e) {
            result = exceptionHandler.apply(e);
        }
        return result;
    }

    static <T> T execute(ThrowableSupplier<T> supplier) throws NullPointerException {
        return execute(supplier, supplier::handleException);
    }


    static <T> T execute(ThrowableSupplier<T> supplier, Function<Throwable, T> exceptionHandler) throws NullPointerException {
        assertNotNull(supplier, "The supplier must not be null");
        return supplier.execute(exceptionHandler);
    }

}
