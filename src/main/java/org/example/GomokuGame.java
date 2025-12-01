package org.example;

import java.util.Scanner;

public class GomokuGame {
    private int[][] board;
    private static final int SIZE = 15; // Board size
    private static final int EMPTY = 0;
    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;
    private int currentPlayer;
    private Scanner scanner;

    public GomokuGame() {
        board = new int[SIZE][SIZE];
        scanner = new Scanner(System.in);
        currentPlayer = PLAYER_1;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void startGame() {
        boolean gameOver = false;
        while (!gameOver) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row column): ");
            int row = scanner.nextInt() - 1; // Subtract 1 for 0-based indexing
            int col = scanner.nextInt() - 1;

            if (isValidMove(row, col)) {
                makeMove(row, col);
                if (checkWin(row, col)) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins! Game Over.");
                    gameOver = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("The game is a draw! Game Over.");
                    gameOver = true;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.close();
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
    }

    private void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_1) ? PLAYER_2 : PLAYER_1;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWin(int row, int col) {
        int winCondition = 5;
        // Check horizontal, vertical, and both diagonals
        // We must check combined counts for both directions (e.g. left + right)
        if (countConsecutive(row, col, 0, 1) + countConsecutive(row, col, 0, -1) -1 >= winCondition) return true; // Horizontal
        if (countConsecutive(row, col, 1, 0) + countConsecutive(row, col, -1, 0) -1 >= winCondition) return true; // Vertical
        if (countConsecutive(row, col, 1, 1) + countConsecutive(row, col, -1, -1) -1 >= winCondition) return true; // Diagonal 1
        if (countConsecutive(row, col, 1, -1) + countConsecutive(row, col, -1, 1) -1 >= winCondition) return true; // Diagonal 2

        return false;
    }

    // Helper method to count consecutive stones in a specific direction (dr, dc)
    private int countConsecutive(int r, int c, int dr, int dc) {
        int count = 0;
        int tempR = r;
        int tempC = c;
        // Start counting from the given spot
        while (tempR >= 0 && tempR < SIZE && tempC >= 0 && tempC < SIZE && board[tempR][tempC] == currentPlayer) {
            count++;
            tempR += dr;
            tempC += dc;
        }
        // NOTE: The checkWin method handles combining the count from opposite directions
        // E.g., count from the left + count from the right
        return count;
    }

    private void printBoard() {
        // Simple console print
        System.out.print("  ");
        for (int c = 0; c < SIZE; c++) {
            System.out.printf("%2d", c + 1);
        }
        System.out.println();

        for (int r = 0; r < SIZE; r++) {
            System.out.printf("%2d", r + 1);
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == EMPTY) {
                    System.out.print(" .");
                } else if (board[r][c] == PLAYER_1) {
                    System.out.print(" X");
                } else {
                    System.out.print(" O");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GomokuGame game = new GomokuGame();
        game.startGame();
    }
}
