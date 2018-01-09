package com.github.olegkoskin.algs4.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FastCollinearPoints {
    private LineSegment[] lineSegments;

    /**
     * Finds all line segments containing 4 or more points.
     *
     * @param originalPoints array
     */
    public FastCollinearPoints(Point[] originalPoints) {
        Objects.requireNonNull(originalPoints);

        Point[] points = originalPoints.clone();
        Arrays.sort(points);

        if (hasDuplicates(points)) {
            throw new IllegalArgumentException();
        }

        List<LineSegment> segments = new ArrayList<>();

        for (int i = 0; i < points.length - 3; i++) {
            Arrays.sort(points);
            Arrays.sort(points, points[i].slopeOrder());
            for (int j = 1; j < points.length - 2; j++) {
                Point p = points[0];
                Point q = points[j];
                if (areCollinear(p, q, points[j + 2]) && !areCollinear(p, q, points[j - 1])) {
                    if (p.compareTo(q) < 0) {
                        int count = j + 2;
                        for (int m = j + 3; m < points.length; m++) {
                            if (!areCollinear(p, q, points[m])) {
                                break;
                            }

                            count++;
                        }

                        segments.add(new LineSegment(p, points[count]));
                    }
                }
            }
        }

        lineSegments = segments.toArray(new LineSegment[0]);
    }

    private static boolean areCollinear(Point p, Point q, Point r) {
        double slope1 = p.slopeTo(q);
        double slope2 = p.slopeTo(r);
        if (slope1 == slope2) {
            return true;
        }
        return false;
    }

    /**
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return lineSegments.length;
    }

    /**
     * @return the line segments
     */
    public LineSegment[] segments() {
        return lineSegments;
    }

    private boolean hasDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            Objects.requireNonNull(points[i]);
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;
    }
}
