package com.github.olegkoskin.algs4.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
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
    public void addFirst(Item e) {
        Objects.requireNonNull(e);

        Node<Item> newNode = new Node<>(e, null, first);

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
    public void addLast(Item e) {
        Objects.requireNonNull(e);

        Node<Item> newItem = new Node<>(e, last, null);

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
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        Item element = first.item;
        Node<Item> next = first.next;

        first.item = null;
        first.next = null;

        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }

        first = next;

        size--;

        return element;
    }

    /**
     * remove and return the item from the end
     *
     * @return the item from the end
     */
    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }

        Item element = last.item;
        Node<Item> prev = last.prev;

        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }

        last = prev;

        size--;

        return element;
    }

    /**
     * @return an iterator over items in order from front to end.
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private static class Node<Item> {
        Item item;
        Node<Item> prev;
        Node<Item> next;

        Node(Item item, Node<Item> prev, Node<Item> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> next;

        DequeIterator() {
            next = first;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Item next() {
            if (next == null) {
                throw new NoSuchElementException();
            }

            Item element = next.item;
            next = next.next;

            return element;
        }
    }
}
