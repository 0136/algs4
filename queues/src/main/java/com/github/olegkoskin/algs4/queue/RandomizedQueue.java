package com.github.olegkoskin.algs4.queue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class RandomizedQueue<E> implements Iterable<E> {

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
    public void enqueue(E e) {
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
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = ThreadLocalRandom.current().nextInt(size);
        Object old = array[index];

        array[index] = null;
        size--;

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }

        if (size > 0 && size == array.length / 4) {
            resize(array.length / 2);
        }

        return (E) old;
    }

    /**
     * @return (but do not remove) a random item
     */
    @SuppressWarnings("unchecked")
    public E sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return (E) array[ThreadLocalRandom.current().nextInt(size)];
    }

    /**
     * @return an independent iterator over items in random order
     */
    public Iterator<E> iterator() {
        return new RandomIterator();
    }

    private void resize(int capacity) {
        array = Arrays.copyOf(array, capacity);
    }

    private class RandomIterator implements Iterator<E> {
        private int cursor;
        private int[] randomIndexes = new int[size];

        RandomIterator() {
            ThreadLocalRandom localRandom = ThreadLocalRandom.current();
            for (int i = 0; i < size; i++) {
                int randomIndex = -1;

                while (exists(randomIndex, randomIndexes)) {
                    randomIndex = localRandom.nextInt(size);
                }

                randomIndexes[i] = randomIndex;
            }
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }

            return (E) array[randomIndexes[cursor++]];
        }

        private boolean exists(int number, int[] array) {
            if (number == -1) {
                return true;
            }

            for (int anArray : array) {
                if (number == anArray) {
                    return true;
                }
            }

            return false;
        }
    }
}
