package com.dianpoint.summer.lang;

import java.util.function.Predicate;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/5 20:43
 */

public interface Predicates {

    Predicate[] EMPTY_PREDICATE_ARRAY = new Predicate[0];


    static <T> Predicate<T>[] emptyArray() {
        return (Predicate<T>[]) EMPTY_PREDICATE_ARRAY;
    }


    /**
     * {@link Predicate} always return <code>true</code>
     *
     * @param <T> the type to test
     * @return <code>true</code>
     */
    static <T> Predicate<T> alwaysTrue() {
        return e -> true;
    }

    /**
     * {@link Predicate} always return <code>false</code>
     *
     * @param <T> the type to test
     * @return <code>false</code>
     */
    static <T> Predicate<T> alwaysFalse() {
        return e -> false;
    }


    static <T> Predicate<? super T> and(Predicate<? super T>... predicates) {
        int length = predicates == null ? 0 : predicates.length;
        if (length == 0) {
            return alwaysTrue();
        }
        if (length == 1) {
            return predicates[0];
        }
        Predicate<T> andPredicate = alwaysTrue();
        for (Predicate<? super T> predicate : predicates) {
            andPredicate = andPredicate.and(predicate);
        }
        return andPredicate;
    }

    /**
     * 输出一个逻辑或运算
     *
     * @param predicates {@link Predicate predicates}
     * @param <T>        被判断的对象
     * @return non-null
     */
    static <T> Predicate<? super T> or(Predicate<? super T>... predicates) {
        int length = predicates == null ? 0 : predicates.length;
        if (length == 0) {
            return alwaysFalse();
        }
        if (length == 1) {
            return predicates[0];
        }

        Predicate<T> orPredicate = alwaysFalse();

        for (Predicate<? super T> predicate : predicates) {
            orPredicate = orPredicate.or(predicate);
        }
        return orPredicate;

    }


}
