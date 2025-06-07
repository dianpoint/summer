package com.dianpoint.summer.lang;

import com.dianpoint.summer.utils.SetUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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

    static <T> List<T> filterList(T[] values, Predicate<? super T> predicate) {
        return filterList(Arrays.asList(values), predicate);
    }

    static <T, S extends Iterable<T>> List<T> filterList(S values, Predicate<? super T> predicate) {
        return filterStream(values, predicate).collect(Collectors.toList());
    }


    static <T, S extends Iterable<T>> Set<T> filterSet(S values, Predicate<? super T> predicate) {
        return filterStream(values, predicate).collect(LinkedHashSet::new, Set::add, Set::addAll);
    }

    static <T> Set<T> filterSet(T[] values, Predicate<T> predicate) {
        return filterSet(Arrays.asList(values), predicate);
    }


    static <T, S extends Iterable<T>> S filter(S values, Predicate<? super T> predicate) {
        boolean isSet = SetUtils.isSet(values);
        if (isSet) {
            return (S) filterSet(values, predicate);
        }
        return (S) filterList(values, predicate);
    }


    //filter逻辑操作

    //通用的filter过滤方法，对于集合iterable类型进行过滤处理
    static <T, S extends Iterable<T>> S filterAll(S values, Predicate<? super T>... predicates) {
        return filter(values, Predicates.and(predicates));
    }

    //对iterable进行逻辑and 逻辑or处理

    static <T> List<T> filterAllList(T[] values, Predicate<? super T>... predicates) {
        return filterAll(Arrays.asList(values), predicates);
    }

    static <T> Set<T> filterAllSet(T[] values, Predicate<? super T>... predicates) {
        return filterAll(SetUtils.ofSet(values), predicates);
    }


    static <T, S extends Iterable<T>> S filterAny(S values, Predicate<? super T>... predicates) {
        return filter(values, Predicates.or(predicates));
    }

    static <T> List<T> filterAnyList(T[] values, Predicate<? super T>... predicates) {
        return filterAny(Arrays.asList(values), predicates);
    }

    static <T> Set<T> filterAnySet(T[] values, Predicate<? super T>... predicates) {
        return filterAny(SetUtils.ofSet(values), predicates);
    }


    //再添加一个findFirst方法
    static <T> T filterFirst(Iterable<T> values, Predicate<T>... predicates) {
        return StreamSupport.stream(values.spliterator(), false)
                .filter(Predicates.and(predicates))
                .findFirst()
                .orElse(null);
    }


}
