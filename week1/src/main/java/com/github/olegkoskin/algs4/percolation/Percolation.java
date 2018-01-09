package com.github.olegkoskin.algs4.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.stream.IntStream;

public class Percolation {
    private static final int OPEN = 1;
    private static final int CONNECT_TO_TOP = 2;
    private static final int CONNECT_TO_BOTTOM = 4;

    private int order;
    private int[] states;
    private int openSiteCount;
    private boolean percolated;
    private WeightedQuickUnionUF siteStorage;

    /**
     * Create n-by-n grid, with all sites blocked.
     *
     * @param n order of percolation grid
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        order = n;

        states = new int[n * n + 1];
        siteStorage = new WeightedQuickUnionUF(n * n + 1);
    }

    /**
     * Open site (row, col) if it is not open already.
     *
     * @param row index
     * @param col index
     */
    public void open(int row, int col) {
        checkRange(row, col);

        if (isOpen(row, col)) {
            return;
        }

        int siteIndex = toSiteIndex(row, col);
        states[siteIndex] |= OPEN;

        if (row == 1) {
            states[siteIndex] |= CONNECT_TO_TOP;
        }

        if (row == order) {
            states[siteIndex] |= CONNECT_TO_BOTTOM;
        }

        int neighborStatuses = neighbors(row, col).build().boxed()
                .map(i -> states[siteStorage.find(i)])
                .reduce(0, (a, b) -> a | b);

        neighbors(row, col).build()
                .forEach(neighborIndex -> connect(siteIndex, neighborIndex));

        neighborStatuses |= states[siteIndex];

        states[siteStorage.find(siteIndex)] |= neighborStatuses;

        percolated = ((neighborStatuses & CONNECT_TO_TOP) == CONNECT_TO_TOP
                && (neighborStatuses & CONNECT_TO_BOTTOM) == CONNECT_TO_BOTTOM);

        openSiteCount++;
    }

    private IntStream.Builder neighbors(int row, int col) {
        IntStream.Builder builder = IntStream.builder();

        if (row > 1 && isOpen(row - 1, col)) { // if the left site is open connect it to left site
            builder.accept(toSiteIndex(row - 1, col));
        }
        if (row < order && isOpen(row + 1, col)) { // if the bottom site is open connect it to bottom site
            builder.accept(toSiteIndex(row + 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) { // if the left site is open connect it to left site
            builder.accept(toSiteIndex(row, col - 1));
        }
        if (col < order && isOpen(row, col + 1)) { // if the right site is open connect it to right site
            builder.accept(toSiteIndex(row, col + 1));
        }

        return builder;
    }


    /**
     * @param row index
     * @param col index
     * @return is site (row, col) open.
     */
    public boolean isOpen(int row, int col) {
        checkRange(row, col);

        return (states[toSiteIndex(row, col)] & OPEN) == OPEN;
    }

    /**
     * @param row index
     * @param col index
     * @return is site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        checkRange(row, col);

        return (states[siteStorage.find(toSiteIndex(row, col))] & CONNECT_TO_TOP) == CONNECT_TO_TOP;
    }

    /**
     * @return number of open sites.
     */
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    /**
     * @return true is the system percolate, otherwise false.
     */
    public boolean percolates() {
        return percolated;
    }

    private int toSiteIndex(int row, int col) {
        return order * (row - 1) + col;
    }

    private void checkRange(int row, int col) {
        if (row < 1 || row > order) {
            throw new IndexOutOfBoundsException("Row index should be between 1 and " + order);
        }

        if (col < 1 || col > order) {
            throw new IndexOutOfBoundsException("Column index should be between 1 and " + order);
        }
    }

    private void connect(int siteIndex1, int siteIndex2) {
        if (siteStorage.connected(siteIndex1, siteIndex2)) {
            return;
        }

        siteStorage.union(siteIndex1, siteIndex2);
    }
}
