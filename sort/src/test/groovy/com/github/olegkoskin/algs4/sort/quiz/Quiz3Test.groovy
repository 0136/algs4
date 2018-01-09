package com.github.olegkoskin.algs4.sort.quiz

import spock.lang.Specification

import static com.github.olegkoskin.algs4.sort.quiz.Quiz3.Pebble.*

class Quiz3Test extends Specification {
    def "test Dutch national flag sorting problem"(){
        expect:
        new Quiz3.Bucket(pebbles).sort().getPebbles() == expected

        where:
        pebbles            | expected
        [BLUE, RED, RED, WHITE, BLUE, WHITE] | [RED, RED, WHITE, WHITE, BLUE, BLUE]
        [RED, WHITE, BLUE, RED, WHITE, BLUE] | [RED, RED, WHITE, WHITE, BLUE, BLUE]
        [RED, WHITE, WHITE, RED, WHITE, BLUE, WHITE, BLUE, RED, RED, RED, WHITE] | [RED, RED, RED, RED, RED, WHITE, WHITE, WHITE, WHITE, WHITE, BLUE, BLUE]
    }
}
