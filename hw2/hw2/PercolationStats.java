package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private double[] X;
    private Percolation P;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("the input is out of bound");
        }
        X = new double[T];
        this.N = N;
        this.T = T;
        P = pf.make(N);
        for (int i = 0; i < T; i++) {
            while(!P.percolates()) {
                P.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            X[i] =(double) P.numberOfOpenSites() / (N * N);
        }

    }

    public double mean() {
        return StdStats.mean(X);
    }

    public double stddev() {
        return StdStats.stddev(X);
    }

    public double confidenceLow() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(T);
    }
}
