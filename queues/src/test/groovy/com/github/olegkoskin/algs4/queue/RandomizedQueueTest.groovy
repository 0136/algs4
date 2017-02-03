package com.github.olegkoskin.algs4.queue

import spock.lang.Specification

class RandomizedQueueTest extends Specification {
    def queue = new RandomizedQueue<String>()

    def "New created queue should be empty"() {
        expect:
        queue.isEmpty()
    }

    def "Size should be 1 after add new item to empty queue"() {
        when:
        queue.enqueue("test")

        then:
        queue.size() == 1
    }

    def "Queue should be empty after add item and then delete"() {
        when:
        queue.enqueue("test")
        def actual = queue.dequeue()
        then:
        actual == "test"
        queue.isEmpty()
    }

    def "Should throw a NullPointerException if the client attempt to add null item"() {
        when:
        queue.enqueue(null)

        then:
        thrown(NullPointerException)
    }

    def "Should throw NoSuchElementException if the client attempts to sample or dequeue an item from an empty queue"() {
        when:
        queue.sample()

        then:
        thrown(NoSuchElementException)

        when:
        queue.dequeue()

        then:
        thrown(NoSuchElementException)
    }

    def "Queue iterator shouldn't be null"() {
        when:
        def iterator = queue.iterator()

        then:
        iterator != null
    }

    def "Should throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator"() {
        when:
        queue.iterator().remove()

        then:
        thrown(UnsupportedOperationException)
    }

    def "Should throw a java.util.NoSuchElementException if the client calls the next() method in the empty iterator"() {
        when:
        ++queue.iterator()

        then:
        thrown(NoSuchElementException)
    }

    def "To sample should return random item and not remove it"() {
        given:
        queue.enqueue("test1")
        queue.enqueue("test2")
        queue.enqueue("test3")

        when:
        def item = queue.sample()

        then:
        queue.size() == 3
        item in ["test1", "test2", "test3"]
    }

    def "To dequeue should return random item and remove it"() {
        given:
        queue.enqueue("test1")
        queue.enqueue("test2")
        queue.enqueue("test3")

        when:
        def item1 = queue.dequeue()
        def item2 = queue.dequeue()
        def item3 = queue.dequeue()

        then:
        queue.size() == 0
        item1 in ["test1", "test2", "test3"]
        item2 in ["test1", "test2", "test3"]
        item3 in ["test1", "test2", "test3"]
    }
}
