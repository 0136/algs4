package com.github._0136.algs4.queue

import spock.lang.Specification

class DequeTest extends Specification {
    def deque = new Deque<String>()

    def "New created deque should be empty"() {
        expect:
        deque.isEmpty()
    }

    def "Size should be 1 after add new item to empty deque"() {
        expect:
        deque.isEmpty()

        when:
        deque.addFirst("f1")

        then:
        deque.size() == 1

    }

    def "Deque should be empty after add new last item and remove fist"() {
        expect:
        deque.isEmpty()

        when:
        deque.addLast("f1")
        deque.removeFirst()

        then:
        deque.isEmpty()
    }

    def "Should throw a NullPointerException if the client attempts to add a null item"() {
        when:
        deque.addFirst(null)

        then:
        thrown(NullPointerException)
        deque.isEmpty()

        when:
        deque.addLast(null)

        then:
        thrown(NullPointerException)
        deque.isEmpty()
    }

    def "Deque iterator shouldn't be null"() {
        when:
        def iterator = deque.iterator()

        then:
        iterator != null
    }

    def "Should throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator"() {
        when:
        deque.iterator().remove()

        then:
        thrown(UnsupportedOperationException)
    }

    def "Should throw a java.util.NoSuchElementException if the client calls the next() method in the empty iterator"() {
        when:
        ++deque.iterator()

        then:
        thrown(NoSuchElementException)
    }

    def "Should throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque"() {
        when:
        deque.removeFirst()

        then:
        thrown(NoSuchElementException)

        when:
        deque.removeLast()

        then:
        thrown(NoSuchElementException)
    }

    def "Should remove and return first element from deque"() {
        given:
        deque.addFirst("test1")
        deque.addFirst("test2")
        deque.addFirst("test3")

        when:
        def string = deque.removeFirst()

        then:
        string == "test3"
    }

    def "Should return correct value after the sequence of addFirst() and removeFirst()"() {
        given:
        deque.addFirst("LINTTTIQTQ")
        deque.addFirst("UFWLWPKGOG")
        deque.addFirst("DUTQSJABLO")
        deque.removeFirst()
        deque.addFirst("KDSMHIHZVZ")
        deque.addFirst("UEPGYWJFYI")
        deque.addFirst("VLPBSZACAB")
        deque.addFirst("UTTTCMDFCZ")
        deque.removeFirst()

        expect:
        deque.removeFirst() == 'VLPBSZACAB'
    }

    def "Should correct connect first and last items"() {
        given:
        deque.addFirst("test")

        when:
        def string = deque.removeLast()

        then:
        string == "test"
    }
}
