package com.github.olegkoskin.algs4.sort;

import java.util.List;

import static com.github.olegkoskin.algs4.sort.SortUtils.exch;
import static com.github.olegkoskin.algs4.sort.SortUtils.less;

public class Selection {

    public static <T extends Comparable<T>> List<T> sort(List<T> list) {
        int N = list.size();

        for (int i = 0; i < N; i++) {
            int min = i;

            for (int j = i + 1; j < N; j++) {
                if (less(list.get(j), list.get(min))) {
                    min = j;
                }
            }

            exch(list, i, min);
        }

        return list;
    }

}
