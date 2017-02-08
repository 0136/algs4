package com.github._0136.algs4.queue;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Object[] array;
    private int size;

    public RandomizedQueue() {
        array = new Object[1];
    }

    /**
     * @return true is the queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return the number of items on the queue
     */
    public int size() {
        return size;
    }

    /**
     * add the item
     *
     * @param e to add
     */
    public void enqueue(Item e) {
        Objects.requireNonNull(e);

        if (size == array.length) {
            resize(size * 2);
        }

        array[size++] = e;
    }

    /**
     *
     * @return random item and remove it
     */
    @SuppressWarnings("unchecked")
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(size);
        Object old = array[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }

        array[--size] = null;

        if (size > 0 && size == array.length / 4) {
            resize(array.length / 2);
        }

        return (Item) old;
    }

    /**
     * @return (but do not remove) a random item
     */
    @SuppressWarnings("unchecked")
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return (Item) array[StdRandom.uniform(size)];
    }

    /**
     * @return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private void resize(int capacity) {
        array = Arrays.copyOf(array, capacity);
    }

    private class RandomIterator implements Iterator<Item> {
        private int cursor;
        private int[] randomIndexes = new int[size];

        RandomIterator() {
            for (int i = 0; i < size; i++) {
                randomIndexes[i] = i;
            }
            StdRandom.shuffle(randomIndexes);
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Item next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }

            return (Item) array[randomIndexes[cursor++]];
        }
    }
}
