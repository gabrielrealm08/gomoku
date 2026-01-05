package org.example.model;

public class Board {
    public static final int SIZE = 15;
    public static final int EMPTY = 0;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;

    private final int[][] grid;

    public Board() {
        grid = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = EMPTY;
            }
        }
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && grid[row][col] == EMPTY;
    }

    public void placeStone(int row, int col, int player) {
        if (!isValidMove(row, col)) {
            throw new IllegalArgumentException("Invalid move at (" + row + ", " + col + ")");
        }
        grid[row][col] = player;
    }

    public int getCell(int row, int col) {
        return grid[row][col];
    }

    public boolean isFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
