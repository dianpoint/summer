package com.dianpoint.summer.utils;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetUtils implements Utils {

    protected static final float FIXED_LOAD_FACTOR = 1.00f;

    public static boolean isSet(Iterable<?> elements) {
        return elements instanceof Set;
    }

    public static <E> Set<E> of(E... elements) {
    return ofSet(elements);
    }

    public static <E> Set<E> ofSet(E... elements) {
        int size = elements == null ? 0 : elements.length;
        if (size < 1) {
            return Collections.emptySet();
        } else if (size == 1) {
            return Collections.singleton(elements[0]);
        }

        Set<E> set = new LinkedHashSet<>(size, FIXED_LOAD_FACTOR);
        for (int i = 0; i < size; i++) {
            set.add(elements[i]);
        }
        return Collections.unmodifiableSet(set);
    }
}
