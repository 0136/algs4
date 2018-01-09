package com.github.olegkoskin.algs4.sort;

import java.util.List;

public final class SortUtils {

    public static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    public static <T extends Comparable<T>> void exch(List<T> list, int i, int min) {
        T temp = list.get(i);
        list.set(i, list.get(min));
        list.set(min, temp);
    }

    private SortUtils() {}
}
