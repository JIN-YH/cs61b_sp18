package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private WeightedQuickUnionUF Union1;
    private WeightedQuickUnionUF Union2;
    private int[][] Pos;
    private int count;
    private int low;
    private int high;
    public Percolation(int N) {
        high = N * N;
        low = high + 1;
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N must be bigger than 0");
        }
        this.N = N;
        Pos = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Pos[i][j] = 0;
            }
        }
        Union1 = new WeightedQuickUnionUF(N * N + 2);
        Union2 = new WeightedQuickUnionUF(N * N + 1);
        count = 0;
    }
    private int xyto2D(int row, int col) {
        return row * N + col;
    }

    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new java.lang.IndexOutOfBoundsException("row or col is out of bound");
        }
        Pos[row][col] = 1;
        count += 1;
        if (row == 0) {
            Union1.union(xyto2D(row, col), high);
            Union2.union(xyto2D(row, col), high);
        }
        if (row == N - 1) {
            Union1.union(xyto2D(row, col), low);
        }
        update(row, col, row - 1, col);
        update(row, col, row + 1, col);
        update(row, col, row, col + 1);
        update(row, col, row, col - 1);
    }

    private void update(int row, int col, int newrow, int newcol) {
        if (newrow < 0 || newrow >= N || newcol < 0 || newcol >= N) {
            return;
        }
        if (Pos[newrow][newcol] == 1) {
            Union1.union(xyto2D(row, col), xyto2D(newrow, newcol));
            Union2.union(xyto2D(row, col), xyto2D(newrow, newcol));
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new java.lang.IndexOutOfBoundsException("row or col is out of bound");
        }
        return Pos[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new java.lang.IndexOutOfBoundsException("row or col is out of bound");
        }
        if (Union2.connected(xyto2D(row, col), high)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        if (Union1.connected(low, high)) {
            return true;
        }
        return false;
    }

}
