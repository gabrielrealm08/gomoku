package org.example.ui;

import org.example.GomokuGame;
import org.example.model.Board;

import java.util.Scanner;

/**
 * Console user interface for the Gomoku game.
 * Handles display and player input.
 */
public class ConsoleUI {
    private final GomokuGame game;  // The game instance
    private final Scanner scanner;  // For reading user input

    /**
     * Creates a new console UI.
     * @param game the game to control
     */
    public ConsoleUI(GomokuGame game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the game loop.
     * Displays the board, gets player input, and checks for win/draw conditions.
     */
    public void start() {
        System.out.println("===== Welcome to Gomoku! =====");
        System.out.println("Rules: Get 5 stones in a row to win!");
        System.out.println("Player 1: X, Player 2: O");
        System.out.println();

        boolean gameOver = false;

        while (!gameOver) {
            // Display the current board
            displayBoard();

            // Get current player
            int player = game.getCurrentPlayer();
            System.out.println("\nPlayer " + player + "'s turn (" + (player == 1 ? "X" : "O") + ")");
            System.out.print("Enter your move (row column): ");

            try {
                // Read player input (1-based indexing for user-friendly input)
                int row = scanner.nextInt() - 1;  // Convert to 0-based
                int col = scanner.nextInt() - 1;  // Convert to 0-based

                // Try to make the move
                if (game.makeMove(row, col)) {
                    // Check if this move won the game
                    if (game.checkWin(row, col)) {
                        displayBoard();
                        System.out.println();
                        System.out.println("*************************************");
                        System.out.println("*** Player " + player + " (" + (player == 1 ? "X" : "O") + ") WINS! ***");
                        System.out.println("*************************************");
                        gameOver = true;
                    } 
                    // Check if the game is a draw
                    else if (game.isDraw()) {
                        displayBoard();
                        System.out.println();
                        System.out.println("===== Game Over - It's a Draw! =====");
                        gameOver = true;
                    } 
                    // Continue to next player
                    else {
                        game.switchPlayer();
                    }
                } else {
                    System.out.println("Invalid move! That cell is occupied or out of bounds. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter two numbers (row and column).");
                scanner.nextLine();  // Clear the invalid input
            }
        }

        scanner.close();
    }

    /**
     * Displays the current game board.
     * Shows row/column numbers and stone positions.
     */
    private void displayBoard() {
        Board board = game.getBoard();

        System.out.println();
        
        // Print column numbers
        System.out.print("   ");
        for (int col = 0; col < Board.SIZE; col++) {
            System.out.printf("%2d ", col + 1);  // 1-based numbering for display
        }
        System.out.println();

        // Print rows with row numbers
        for (int row = 0; row < Board.SIZE; row++) {
            System.out.printf("%2d ", row + 1);  // 1-based row number

            // Print each cell in the row
            for (int col = 0; col < Board.SIZE; col++) {
                int cell = board.getCell(row, col);
                
                if (cell == Board.EMPTY) {
                    System.out.print(" . ");  // Empty cell
                } else if (cell == Board.PLAYER_1) {
                    System.out.print(" X ");  // Player 1
                } else {
                    System.out.print(" O ");  // Player 2
                }
            }
            System.out.println();  // New line after each row
        }
    }
}
