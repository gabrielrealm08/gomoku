package org.example.ui;

import org.example.GomokuGame;
import org.example.model.Board;

import java.util.Scanner;

public class ConsoleUI {
    private final GomokuGame game;
    private final Scanner scanner;

    public ConsoleUI(GomokuGame game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("===== Welcome to Gomoku! =====");
        System.out.println("Rules: Get 5 stones in a row to win!");
        System.out.println("Player 1: X, Player 2: O");
        System.out.println();

        boolean gameOver = false;

        while (!gameOver) {
            displayBoard();

            int player = game.getCurrentPlayer();
            System.out.println("\nPlayer " + player + "'s turn (" + (player == 1 ? "X" : "O") + ")");
            System.out.print("Enter your move (row column): ");

            try {
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;

                if (game.makeMove(row, col)) {
                    if (game.checkWin(row, col)) {
                        displayBoard();
                        System.out.println();
                        System.out.println("*************************************");
                        System.out.println("*** Player " + player + " (" + (player == 1 ? "X" : "O") + ") WINS! ***");
                        System.out.println("*************************************");
                        gameOver = true;
                    } 
                    else if (game.isDraw()) {
                        displayBoard();
                        System.out.println();
                        System.out.println("===== Game Over - It's a Draw! =====");
                        gameOver = true;
                    } 
                    else {
                        game.switchPlayer();
                    }
                } else {
                    System.out.println("Invalid move! That cell is occupied or out of bounds. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter two numbers (row and column).");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private void displayBoard() {
        Board board = game.getBoard();

        System.out.println();
        
        System.out.print("   ");
        for (int col = 0; col < Board.SIZE; col++) {
            System.out.printf("%2d ", col + 1);
        }
        System.out.println();

        for (int row = 0; row < Board.SIZE; row++) {
            System.out.printf("%2d ", row + 1);

            for (int col = 0; col < Board.SIZE; col++) {
                int cell = board.getCell(row, col);
                
                if (cell == Board.EMPTY) {
                    System.out.print(" . ");
                } else if (cell == Board.PLAYER_1) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" O ");
                }
            }
            System.out.println();
        }
    }
}
