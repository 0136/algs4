package com.github.olegkoskin.algs4.sort.quiz;

import com.github.olegkoskin.algs4.sort.Shell;

import java.util.List;

/**
 * Permutation. Given two integer arrays of size n, design a subquadratic algorithm to determine
 * whether one is a permutation of the other. That is, do they contain exactly the same entries but,
 * possibly, in a different order.
 */
public class Quiz2 {

    public static boolean isPermutation(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }

        Shell.sort(a);
        Shell.sort(b);

        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return false;
            }
        }

        return true;
    }
}
