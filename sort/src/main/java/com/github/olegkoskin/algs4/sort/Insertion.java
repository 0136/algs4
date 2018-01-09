package com.github.olegkoskin.algs4.sort;

import java.util.List;

import static com.github.olegkoskin.algs4.sort.SortUtils.exch;
import static com.github.olegkoskin.algs4.sort.SortUtils.less;

public class Insertion {

    public static <T extends Comparable<T>> List<T> sort(List<T> list) {
        int N = list.size();

        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(list.get(j), list.get(j - 1))) {
                    exch(list, j, j - 1);
                } else break;
            }
        }

        return list;
    }
}
