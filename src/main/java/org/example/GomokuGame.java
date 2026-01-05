package org.example;

import org.example.model.Board;

/**
 * Main game logic for Gomoku.
 * Handles game flow, win detection, and player turns.
 */
public class GomokuGame {
    private final Board board;          // The game board
    private int currentPlayer;          // Current player (1 or 2)

    /**
     * Creates a new Gomoku game.
     */
    public GomokuGame() {
        this.board = new Board();
        this.currentPlayer = Board.PLAYER_1;  // Player 1 starts
    }

    /**
     * Gets the current player.
     * @return the current player ID (1 or 2)
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the game board.
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Makes a move for the current player.
     * @param row the row position (0-14)
     * @param col the column position (0-14)
     * @return true if the move was successful, false if invalid
     */
    public boolean makeMove(int row, int col) {
        if (!board.isValidMove(row, col)) {
            return false;
        }
        board.placeStone(row, col, currentPlayer);
        return true;
    }

    /**
     * Checks if the last move resulted in a win.
     * A player wins by getting 5 stones in a row (horizontally, vertically, or diagonally).
     * @param row the row of the last move
     * @param col the column of the last move
     * @return true if the current player has won
     */
    public boolean checkWin(int row, int col) {
        int winCondition = 5;  // Need 5 in a row to win

        // Check all four directions: horizontal, vertical, and both diagonals
        // For each direction, count stones in both directions and combine
        
        // Horizontal: left + right
        if (countDirection(row, col, 0, 1) + countDirection(row, col, 0, -1) - 1 >= winCondition) {
            return true;
        }
        
        // Vertical: up + down
        if (countDirection(row, col, 1, 0) + countDirection(row, col, -1, 0) - 1 >= winCondition) {
            return true;
        }
        
        // Diagonal (top-left to bottom-right)
        if (countDirection(row, col, 1, 1) + countDirection(row, col, -1, -1) - 1 >= winCondition) {
            return true;
        }
        
        // Diagonal (top-right to bottom-left)
        if (countDirection(row, col, 1, -1) + countDirection(row, col, -1, 1) - 1 >= winCondition) {
            return true;
        }

        return false;
    }

    /**
     * Counts consecutive stones in a specific direction.
     * @param row starting row
     * @param col starting column
     * @param rowDelta row direction (-1, 0, or 1)
     * @param colDelta column direction (-1, 0, or 1)
     * @return count of consecutive stones in that direction
     */
    private int countDirection(int row, int col, int rowDelta, int colDelta) {
        int count = 0;
        int currentRow = row;
        int currentCol = col;

        // Count stones in the specified direction while they match the current player
        while (currentRow >= 0 && currentRow < Board.SIZE && 
               currentCol >= 0 && currentCol < Board.SIZE && 
               board.getCell(currentRow, currentCol) == currentPlayer) {
            count++;
            currentRow += rowDelta;
            currentCol += colDelta;
        }

        return count;
    }

    /**
     * Checks if the game is a draw (board is full with no winner).
     * @return true if the board is full
     */
    public boolean isDraw() {
        return board.isFull();
    }

    /**
     * Switches to the other player.
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == Board.PLAYER_1) ? Board.PLAYER_2 : Board.PLAYER_1;
    }
}