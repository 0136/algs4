package com.github.olegkoskin.algs4.queue;

import java.util.Iterator;

public class Deque<E> implements Iterable<E> {

    private int size = 0;

    public Deque() {
    }

    // unit testing (optional)
    public static void main(String[] args) {
    }

    /**
     * @return true the deque is empty, otherwise false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * add the item to the front
     *
     * @param e
     */
    public void addFirst(E e) {
    }

    /**
     * add the item to the end
     *
     * @param e
     */
    public void addLast(E e) {
    }

    /**
     * remove and return the item from the front
     *
     * @return
     */
    public E removeFirst() {
        return null;
    }

    /**
     * remove and return the item from the end
     *
     * @return
     */
    public E removeLast() {
        return null;
    }

    /**
     * @return an iterator over items in order from front to end.
     */
    public Iterator<E> iterator() {
        return null;
    }
}
