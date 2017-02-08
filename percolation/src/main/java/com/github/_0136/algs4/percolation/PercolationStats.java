package com.github._0136.algs4.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int t;
    private double[] means;
    private int size;

    /**
     * Perform trials independent experiments on an n-by-n grid.
     *
     * @param n      is order of grid
     * @param trials is number of experiments.
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and/or trial should be more 0.");
        }

        t = trials;
        size = n * n;
        means = new double[t];

        for (int i = 0; i < t; ++i) {
            Percolation percolation = new Percolation(n);

            for (int j = 0; j < n * n; j++) {

                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);

                if (percolation.isOpen(row, col)) {
                    continue;
                }

                percolation.open(row, col);

                if (percolation.percolates()) {
                    break;
                }
            }

            means[i] = (double) percolation.numberOfOpenSites() / size;
        }
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percStats = new PercolationStats(n, trials);

        System.out.println("mean                    = " + percStats.mean());
        System.out.println("stddev                  = " + percStats.stddev());
        System.out.printf("95%% confidence interval = %f, %f", percStats.confidenceLo(), percStats.confidenceHi());
    }

    /**
     * @return sample mean of percolation threshold.
     */
    public double mean() {
        return StdStats.mean(means);
    }

    /**
     * @return sample standard deviation of percolation threshold.
     */
    public double stddev() {
        return StdStats.stddev(means);
    }

    /**
     * @return low  endpoint of 95% confidence interval.
     */
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(t);
    }

    /**
     * @return high endpoint of 95% confidence interval.
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    }
}
