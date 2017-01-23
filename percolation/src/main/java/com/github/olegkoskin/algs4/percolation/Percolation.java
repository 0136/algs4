package com.github.olegkoskin.algs4.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.stream.IntStream;

public class Percolation {
    private int order;
    private int virtualTopSite;
    private int virtualBottomSite;
    private boolean[] states;
    private int openSiteCount;
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

        virtualTopSite = 0;
        virtualBottomSite = n * n + 1;

        openSiteCount = 0;
        states = new boolean[n * n + 2];
        siteStorage = new WeightedQuickUnionUF(n * n + 2);
    }

    public static void main(String[] args) {   // test client (optional)
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

        if (row == 1) {
            siteStorage.union(virtualTopSite, siteIndex);
        }

        if (row == order) {
            siteStorage.union(virtualBottomSite, siteIndex);
        }

        IntStream.of(toSiteIndex(row, col - 1), toSiteIndex(row - 1, col), toSiteIndex(row, col + 1), toSiteIndex(row + 1, col))
                .filter(i -> i > 1 && i < virtualBottomSite)
                .forEach(i -> connectIfFirstOpen(i, siteIndex));

        states[toSiteIndex(row, col)] = true;
        openSiteCount++;
    }

    /**
     * @param row index
     * @param col index
     * @return is site (row, col) open.
     */
    public boolean isOpen(int row, int col) {
        return states[toSiteIndex(row, col)];
    }

    /**
     * @param row index
     * @param col index
     * @return is site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        return siteStorage.connected(virtualTopSite, toSiteIndex(row, col));
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
    public boolean percolates() {             // does the system percolate?
        return siteStorage.connected(virtualTopSite, virtualBottomSite);
    }

    private int toSiteIndex(int row, int col) {
        return order * --row + col;
    }

    private void checkRange(int row, int col) {
        if (row < 1 || row > order) {
            throw new IndexOutOfBoundsException("Row index should be between 1 and " + order);
        }

        if (col < 1 || col > order) {
            throw new IndexOutOfBoundsException("Column index should be between 1 and " + order);
        }
    }

    private void connectIfFirstOpen(int siteIndex1, int siteIndex2) {
        if (!isOpen(1 + siteIndex1 / order, siteIndex1 % order) || siteStorage.connected(siteIndex1, siteIndex2)) {
            return;
        }

        siteStorage.union(siteIndex1, siteIndex2);
    }
}
