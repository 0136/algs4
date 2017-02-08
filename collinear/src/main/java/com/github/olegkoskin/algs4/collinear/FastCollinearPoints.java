package com.github.olegkoskin.algs4.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FastCollinearPoints {
    private List<LineSegment> lineSegments;

    /**
     * Finds all line segments containing 4 or more points.
     *
     * @param points array
     */
    public FastCollinearPoints(Point[] points) {
        Objects.requireNonNull(points);

        Point[] copyPoints = points.clone();
        Arrays.sort(copyPoints);

        if (hasDuplicates(copyPoints)) {
            throw new IllegalArgumentException();
        }

        lineSegments = new ArrayList<>();

        for (Point point : points) {
            Arrays.sort(copyPoints, point.slopeOrder());

            double slope = -1.;
            int first = 0;
            int last = 1;

            /*while (last < copyPoints.length && Double.compare(point.slopeTo(copyPoints[first]), point.slopeTo(copyPoints[last])) == 0) {
                last++;
            }*/
        }
    }

    /**
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return lineSegments.size();
    }

    /**
     * @return the line segments
     */
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }

    private boolean hasDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;
    }
}
