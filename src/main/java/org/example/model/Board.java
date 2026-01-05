package org.example.model;

/**
 * Represents the Gomoku game board.
 * The board is a 15x15 grid where players place their stones.
 */
public class Board {
    // Board configuration constants
    public static final int SIZE = 15;        // Standard Gomoku board size
    public static final int EMPTY = 0;        // Empty cell marker
    public static final int PLAYER_1 = 1;     // Player 1 marker (X)
    public static final int PLAYER_2 = 2;     // Player 2 marker (O)

    private final int[][] grid;               // The game board grid

    /**
     * Creates a new empty board.
     */
    public Board() {
        grid = new int[SIZE][SIZE];
        // Initialize all cells to EMPTY
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = EMPTY;
            }
        }
    }

    /**
     * Checks if a move is valid.
     * @param row the row index (0-14)
     * @param col the column index (0-14)
     * @return true if the move is within bounds and the cell is empty
     */
    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && grid[row][col] == EMPTY;
    }

    /**
     * Places a stone on the board.
     * @param row the row index
     * @param col the column index
     * @param player the player number (1 or 2)
     */
    public void placeStone(int row, int col, int player) {
        if (!isValidMove(row, col)) {
            throw new IllegalArgumentException("Invalid move at (" + row + ", " + col + ")");
        }
        grid[row][col] = player;
    }

    /**
     * Gets the value at a specific cell.
     * @param row the row index
     * @param col the column index
     * @return the cell value (EMPTY, PLAYER_1, or PLAYER_2)
     */
    public int getCell(int row, int col) {
        return grid[row][col];
    }

    /**
     * Checks if the board is full (no empty cells).
     * @return true if all cells are occupied
     */
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
