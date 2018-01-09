package com.github.olegkoskin.algs4.sort;

import java.util.List;

import static com.github.olegkoskin.algs4.sort.SortUtils.exch;
import static com.github.olegkoskin.algs4.sort.SortUtils.less;

public class Shell {

    public static <T extends Comparable<T>> List<T> sort(List<T> list) {
        int N = list.size();

        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(list.get(j), list.get(j - h))) {
                        exch(list, j, j - h);
                    } else break;
                }
            }

            h /= 3;
        }

        return list;
    }
}
