package com.github.olegkoskin.algs4.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int t;
    private double[] results;

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
        results = new double[t];

        for (int i = 0; i < t; i++) {
            Percolation perc = new Percolation(n);

            for (int j = 0; j < n * n; j++) {
                perc.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));

                if (perc.percolates()) {
                    break;
                }
            }

            results[i] = 1. * perc.numberOfOpenSites() / (n * n);
        }
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);

        PercolationStats percStats = new PercolationStats(n, trials);

        System.out.println("mean                    = " + percStats.mean());
        System.out.println("stddev                  = " + percStats.stddev());
        System.out.printf("95%% confidence interval = %f, %f", percStats.confidenceLo(), percStats.confidenceHi());
    }

    /**
     * @return sample mean of percolation threshold.
     */
    public double mean() {
        return StdStats.mean(results);
    }

    /**
     * @return sample standard deviation of percolation threshold.
     */
    public double stddev() {
        return StdStats.stddev(results);
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
