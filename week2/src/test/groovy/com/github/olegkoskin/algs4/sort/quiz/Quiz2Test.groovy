package com.github.olegkoskin.algs4.sort.quiz

import spock.lang.Specification

class Quiz2Test extends Specification {
    def "test isPermutation"() {
        expect:
        Quiz2.isPermutation(a, b) == result

        where:
        a | b | result
        [1, 2, 3, 4, 5, 6, 7, 8, 9] | [1, 3, 5, 7, 9, 2, 4, 6, 8] || true
        [1, 2, 3, 4, 5, 6, 7, 8, 9] | [1, 3, 4, 6] || false
        [1, 3, 5, 7, 9, 2, 4, 6, 8] | [1, 3, 5, 7, 9, 2, 4, 6, 8] || true
    }
}
