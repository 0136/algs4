package com.github.olegkoskin.algs4.sort.quiz;

import java.util.List;

/**
 * Dutch national flag. Given an array of n buckets, each containing a red, white, or blue pebble,
 * sort them by color. The allowed operations are:
 *
 * swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
 * color(i): color of pebble in bucket i.
 *
 * The performance requirements are as follows:
 *
 * At most n calls to color().
 * At most n calls to swap().
 * Constant extra space.
 */
public class Quiz3 {

    public enum Pebble {
        RED, WHITE, BLUE
    }

    public static class Bucket {
        private List<Pebble> pebbles;

        public Bucket(List<Pebble> pebbles) {
            this.pebbles = pebbles;
        }

        public List<Pebble> getPebbles() {
            return pebbles;
        }

        public Bucket sort() {
            int low = 0;
            int high = pebbles.size() - 1;
            int mid = low;

            while (mid <= high) {
                switch (pebbles.get(mid)) {
                    case RED:
                        swap(mid, low);
                        low++;
                        mid++;

                        break;
                    case WHITE:
                        mid++;

                        break;
                    case BLUE:
                        swap(mid, high);
                        high--;

                        break;
                }
            }

            return this;
        }

        private Pebble color(int i) {
            return pebbles.get(i);
        }

        private void swap(int i, int j) {
            Pebble tmp = pebbles.get(i);
            pebbles.set(i, pebbles.get(j));
            pebbles.set(j, tmp);
        }
    }
}
