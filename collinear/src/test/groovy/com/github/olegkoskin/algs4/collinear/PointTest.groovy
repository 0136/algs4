package com.github.olegkoskin.algs4.collinear

import spock.lang.Specification


class PointTest extends Specification {
    def "The slope between (1, 2) and (4, 2) (horizontal line) should be +0.0"() {
        given:
        def p0 = new Point(1, 2)
        def p1 = new Point(4, 2)

        expect:
        new Double(p0.slopeTo(p1)) == new Double(+0.0)
    }

    def "The slope between points (1, 2) and (-2, 2) (horizontal line) should be +0.0"() {
        given:
        def p0 = new Point(1, 2)
        def p1 = new Point(-2, 2)

        expect:
        new Double(p0.slopeTo(p1)) == new Double(+0.0)
    }

    def "The slope between points (1, 1) and (1, 5) (vertical line) should be Double.POSITIVE_INFINITY"() {
        given:
        def p0 = new Point(1, 1)
        def p1 = new Point(1, 5)

        expect:
        new Double(p0.slopeTo(p1)) == Double.POSITIVE_INFINITY
    }

    def "The slope between points (1, 1) and (1, -2) (vertical line) should be Double.POSITIVE_INFINITY"() {
        given:
        def p0 = new Point(1, 1)
        def p1 = new Point(1, -2)

        expect:
        new Double(p0.slopeTo(p1)) == Double.POSITIVE_INFINITY
    }

    def "The slope of a degenerate line segment (between a point and itself) is Double.NEGATIVE_INFINITY"() {
        given:
        def p0 = new Point(1, 1)
        def p1 = new Point(1, 1)

        expect:
        new Double(p0.slopeTo(p1)) == Double.NEGATIVE_INFINITY
    }

    def "Point (0, 0) should be less than the point (2, 4)"() {
        expect:
        new Point(0, 0) < new Point(2, 4)
    }

    def "Point (0, 0) should be less than the point (4, 0)"() {
        expect:
        new Point(0, 0) < new Point(4, 0)
    }

    def "Point (1, 5) should be equal to the point (1, 5)"() {
        expect:
        new Point(1, 5) == new Point(1, 5)
    }

    def "Point (0, 5) should be greater than the point (0, 4)"() {
        expect:
        new Point(0, 5) > new Point(0, 4)
    }

    def "Point (10, 5) should be greater than the point (2, 4)"() {
        expect:
        new Point(10, 5) > new Point(2, 4)
    }

    def "test slopeOrder"() {
        given:
        def p0 = new Point(0, 0)
        /*
                   |
                 1 -
                   |
                   |
            --|----|----|--
             -1    |    1
                   |
                -1 -
                   |
         */
        List<Point> points = [[0, -1], [1, 0], [0, 1], [0, 0], [1, -1], [-1, -1]] as Point[]
        // -Infinity -1 0 1 Infinity Infinity
        List expected = [[0, 0], [1, -1], [1, 0], [-1, -1], [0, -1], [0, 1]] as Point[]

        when:
        points.sort(p0.slopeOrder())

        then:
        points == expected
    }
}
