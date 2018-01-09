package com.github.olegkoskin.algs4.sort

import spock.lang.Specification

class SortTest extends Specification {
    def "Selection sort"() {
        expect:
        Selection.sort(a).equals(b) == c

        where:
        a                               | b                               || c
        [6, 5, 8, 7]                    | [5, 6, 7, 8]                    || true
        [7, 10, 5, 3, 8, 4, 2, 9, 6, 1] | [1, 2, 3, 4, 5, 6, 7, 8, 9, 10] || true
        [1, 2, 3, 4]                    | [4, 3, 2, 1]                    || false
    }

    def "Insertion sort"() {
        expect:
        Insertion.sort(a).equals(b) == c

        where:
        a                               | b                               || c
        [6, 5, 8, 7]                    | [5, 6, 7, 8]                    || true
        [7, 10, 5, 3, 8, 4, 2, 9, 6, 1] | [1, 2, 3, 4, 5, 6, 7, 8, 9, 10] || true
        [1, 2, 3, 4]                    | [4, 3, 2, 1]                    || false
    }

    def "Shell sort"() {
        expect:
        Shell.sort(a).equals(b) == c

        where:
        a                               | b                               || c
        [6, 5, 8, 7]                    | [5, 6, 7, 8]                    || true
        [7, 10, 5, 3, 8, 4, 2, 9, 6, 1] | [1, 2, 3, 4, 5, 6, 7, 8, 9, 10] || true
        [1, 2, 3, 4]                    | [4, 3, 2, 1]                    || false
    }
}
