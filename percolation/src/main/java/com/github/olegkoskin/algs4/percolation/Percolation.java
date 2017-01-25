package com.github.olegkoskin.algs4.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Percolation {
    private int order;
    private List<EnumSet<SiteStatus>> states;
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

        openSiteCount = 0;
        states = Stream.generate(() -> EnumSet.noneOf(SiteStatus.class))
                .limit(n * n + 1)
                .collect(Collectors.toList());
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
        states.get(siteIndex).add(SiteStatus.OPEN);

        if (row == 1) {
            states.get(siteIndex).add(SiteStatus.CONNECT_TO_TOP);
        }

        if (row == order) {
            states.get(siteIndex).add(SiteStatus.CONNECT_TO_BOTTOM);
        }

        EnumSet<SiteStatus> neighborStatuses = neighbors(row, col).build().boxed()
                .flatMap(neighborIndex -> states.get(siteStorage.find(neighborIndex)).stream())
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(SiteStatus.class)));

        neighbors(row, col).build()
                .forEach(neighborIndex -> connect(siteIndex, neighborIndex));

        neighborStatuses.addAll(states.get(siteIndex));

        states.get(siteStorage.find(siteIndex)).addAll(neighborStatuses);

        percolated = neighborStatuses.containsAll(EnumSet.of(SiteStatus.CONNECT_TO_TOP, SiteStatus.CONNECT_TO_BOTTOM));

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
        return states.get(toSiteIndex(row, col)).contains(SiteStatus.OPEN);
    }

    /**
     * @param row index
     * @param col index
     * @return is site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        return states.get(siteStorage.find(toSiteIndex(row, col))).contains(SiteStatus.CONNECT_TO_TOP);
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

    private void connectIfFirstOpen(int siteIndex1, int siteIndex2) {
        if (!isOpen(1 + siteIndex1 / order, siteIndex1 % order) || siteStorage.connected(siteIndex1, siteIndex2)) {
            return;
        }

        siteStorage.union(siteIndex1, siteIndex2);
    }

    private void connect(int siteIndex1, int siteIndex2) {
        if (siteStorage.connected(siteIndex1, siteIndex2)) {
            return;
        }

        siteStorage.union(siteIndex1, siteIndex2);
    }

    private enum SiteStatus {
        OPEN, CONNECT_TO_TOP, CONNECT_TO_BOTTOM;
    }
}
