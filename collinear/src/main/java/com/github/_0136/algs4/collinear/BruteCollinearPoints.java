package com.github._0136.algs4.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BruteCollinearPoints {
    private List<LineSegment> lineSegments;

    /**
     * finds all line segments containing 4 points
     *
     * @param points array
     */
    public BruteCollinearPoints(Point[] points) {
        Objects.requireNonNull(points);

        Point[] copyPoints = points.clone();
        Arrays.sort(copyPoints);

        if (hasDuplicates(copyPoints)) {
            throw new IllegalArgumentException();
        }

        lineSegments = new ArrayList<>();

        for (int p = 0; p < copyPoints.length - 3; p++) {
            for (int q = p + 1; q < copyPoints.length - 2; q++) {
                double slopePQ = copyPoints[p].slopeTo(copyPoints[q]);
                for (int r = q + 1; r < copyPoints.length - 1; r++) {
                    double slopePR = copyPoints[p].slopeTo(copyPoints[r]);
                    if (slopePQ != slopePR) {
                        continue;
                    }

                    for (int s = r + 1; s < copyPoints.length; s++) {
                        double slopePS = copyPoints[p].slopeTo(copyPoints[s]);

                        if (slopePQ != slopePS) {
                            continue;
                        }

                        lineSegments.add(new LineSegment(copyPoints[p], copyPoints[s]));
                    }
                }
            }
        }
    }

    private boolean hasDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;
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
}
