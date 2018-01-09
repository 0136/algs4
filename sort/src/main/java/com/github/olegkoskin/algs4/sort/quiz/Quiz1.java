package com.github.olegkoskin.algs4.sort.quiz;

import com.github.olegkoskin.algs4.sort.Shell;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Intersection of two sets. Given two arrays a[] and b[], each containing n distinct 2D points in the plane,
 * design a subquadratic algorithm to count the number of points that are contained both in array a[] and
 * array b[].
 */
public class Quiz1 {

    public static class Point implements Comparable<Point> {

        private int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point that) {
            if (that.x > x) {
                return -1;
            }

            if (that.x < x) {
                return 1;
            }

            return Integer.compare(y, that.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y);
        }
    }

    public static int countIntersection(List<Point> a, List<Point> b) {
        int count = 0;

        Shell.sort(a);
        Shell.sort(b);

        // variant 1
        for (Point point: a) {
            if (Collections.binarySearch(b, point) >= 0) {
                count++;
            }
        }

        int i = 0, j = 0;
        //count = 0;

        // variant 2
        /*while (i < a.size() && j < b.size()) {
            int compare = a.get(i).compareTo(b.get(j));
            if (compare == 0) {
                count++;
                i++;
                j++;
            } else if (compare < 0) {
                i++;
            } else {
                j++;
            }
        }*/

        return count;
    }
}
