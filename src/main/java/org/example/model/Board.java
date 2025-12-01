package org.example.model;

import java.util.Arrays;

public class Board {
    public static final int SIZE = 15;
    public static final int EMPTY = 0;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;

    private final int[][] grid;

    public Board() {
        grid = new int[SIZE][SIZE];
        for (int[] row : grid) Arrays.fill(row, EMPTY);
    }

    public synchronized boolean isValidMove(int r, int c) {
        return r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] == EMPTY;
    }

    public synchronized void placeMove(Move m) {
        if (!isValidMove(m.getRow(), m.getCol())) throw new IllegalArgumentException("Invalid move");
        grid[m.getRow()][m.getCol()] = m.getPlayer();
    }

    public synchronized int get(int r, int c) { return grid[r][c]; }

    public synchronized boolean isFull() {
        for (int r = 0; r < SIZE; r++) for (int c = 0; c < SIZE; c++) if (grid[r][c] == EMPTY) return false;
        return true;
    }

    public synchronized int[][] snapshot() {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) copy[i] = Arrays.copyOf(grid[i], SIZE);
        return copy;
    }
}
