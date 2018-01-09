package com.github.olegkoskin.algs4.sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.List;

import static com.github.olegkoskin.algs4.sort.SortUtils.exch;

public class Shuflle {

    public static <T extends Comparable<T>> List<T> sort(List<T> list) {
        int N = list.size();
        for (int i = 0; i < N; i++) {
            int r = StdRandom.uniform(i + 1);
            exch(list, i, r);
        }
        
        return list;
    }
}
