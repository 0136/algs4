package com.github.olegkoskin.algs4.sort.quiz

import spock.lang.Shared
import spock.lang.Specification

class Quiz1Test extends Specification {
    @Shared
    def p1 = new Quiz1.Point(1, 1);
    @Shared
    def p2 = new Quiz1.Point(1, 4);
    @Shared
    def p3 = new Quiz1.Point(3, 1);
    @Shared
    def p4 = new Quiz1.Point(5, 1);
    @Shared
    def p5 = new Quiz1.Point(7, 2);
    @Shared
    def p6 = new Quiz1.Point(3, 6);

    def "test countIntersection"() {
        expect:
        Quiz1.countIntersection(a, b) == count

        where:
        a                        | b                || count
        [p1, p2, p3, p4]         | [p5, p6]         || 0
        [p1, p2, p3, p4]         | [p1, p3, p5, p6] || 2
        [p1, p2, p3, p4]         | [p1, p2, p3, p6] || 3
        [p1, p2, p3, p4, p5, p6] | [p1, p3, p5, p6] || 4
        /*a << [[p1, p2, p3, p4]]
        b << [[p5, p6]]
        count << [0]*/
    }
}
