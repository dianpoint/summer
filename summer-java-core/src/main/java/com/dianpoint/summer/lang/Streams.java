package com.dianpoint.summer.lang;

import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/5 20:43
 */

public interface Streams {

    static <T> Stream<T> stream(T... values) {
        return Stream.of(values);
    }

    static <T> Stream<T> stream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }


    static <T> Stream<T> filterStream(T[] values, Predicate<? super T> predicate) {
        Stream<T> stream = stream(values);
        return stream.filter(predicate);
    }

    static <T, S extends Iterable<T>> Stream<T> filterStream(S values, Predicate<? super T> predicate) {
        return stream(values).filter(predicate);
    }
}
