package com.github.olegkoskin.algs4.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Deque<E> implements Iterable<E> {

    private Node<E> first;
    private Node<E> last;
    private int size = 0;

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
     * @param e the item to the front
     */
    public void addFirst(E e) {
        Objects.requireNonNull(e);

        Node<E> newNode = new Node<>(e, null, first);

        if (first == null) {
            last = newNode;
        } else {
            first.prev = newNode;
        }

        first = newNode;
        size++;
    }

    /**
     * add the item to the end
     *
     * @param e the item to add to the end
     */
    public void addLast(E e) {
        Objects.requireNonNull(e);

        Node<E> newItem = new Node<>(e, last, null);

        if (last == null) {
            first = newItem;
        } else {
            last.next = newItem;
        }

        last = newItem;
        size++;
    }

    /**
     * remove and return the item from the front
     *
     * @return the item from the front
     */
    public E removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        E element = first.item;
        Node<E> next = first.next;

        first.item = null;
        first.next = null;

        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }

        size--;

        return element;
    }

    /**
     * remove and return the item from the end
     *
     * @return the item from the end
     */
    public E removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }

        E element = last.item;
        Node<E> prev = last.prev;

        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }

        size--;

        return element;
    }

    /**
     * @return an iterator over items in order from front to end.
     */
    public Iterator<E> iterator() {
        return new DequeIterator();
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private class DequeIterator implements Iterator<E> {
        private Node<E> next;

        DequeIterator() {
            next = first;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            if (next == null) {
                throw new NoSuchElementException();
            }

            E element = next.item;
            next = next.next;

            return element;
        }
    }
}
