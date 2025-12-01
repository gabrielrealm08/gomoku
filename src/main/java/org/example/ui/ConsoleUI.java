package org.example.ui;

import org.example.factory.PlayerFactory;
import org.example.model.Board;
import org.example.model.Move;
import org.example.persistence.GameDao;
import org.example.service.GameService;
import org.example.ai.MoveStrategy;
import org.example.ai.RandomStrategy;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);
    private final GameService service;
    private final MoveStrategy aiStrategy;

    public ConsoleUI(GameService service, MoveStrategy aiStrategy) {
        this.service = service;
        this.aiStrategy = aiStrategy;
    }

    public void start() {
        System.out.println("Welcome to Gomoku");
        printBoard();
        while (true) {
            int player = service.getCurrentPlayer();
            try {
                if (player == Board.PLAYER_1) {
                    System.out.print("Player " + player + " enter move (row col): ");
                    if (!scanner.hasNextInt()) { System.out.println("Invalid input — enter two integers."); scanner.next(); continue; }
                    int r = scanner.nextInt()-1;
                    if (!scanner.hasNextInt()) { System.out.println("Invalid input — enter two integers."); scanner.next(); continue; }
                    int c = scanner.nextInt()-1;
                    boolean ok = service.makeMove(r, c);
                    if (!ok) { System.out.println("Invalid move. Try again."); continue; }
                    if (service.checkWin(r, c)) { printBoard(); System.out.println("Player " + player + " wins!"); break; }
                } else {
                    // simple AI turn
                    Move m = aiStrategy.chooseMove(service.getBoard(), player);
                    if (m == null) { System.out.println("No moves left — draw."); break; }
                    service.makeMove(m.getRow(), m.getCol());
                    System.out.println("AI placed at " + (m.getRow()+1) + "," + (m.getCol()+1));
                    if (service.checkWin(m.getRow(), m.getCol())) { printBoard(); System.out.println("Player " + player + " (AI) wins!"); break; }
                }
                if (service.isDraw()) { printBoard(); System.out.println("Draw!"); break; }
                service.switchPlayer();
                printBoard();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again."); scanner.nextLine();
            }
        }
        System.out.println("Game over.");
    }

    private void printBoard() {
        int[][] snap = service.getBoard().snapshot();
        System.out.print("   ");
        for (int c = 0; c < Board.SIZE; c++) System.out.printf("%2d", c+1);
        System.out.println();
        for (int r=0;r<Board.SIZE;r++){
            System.out.printf("%2d ", r+1);
            for (int c=0;c<Board.SIZE;c++){
                int v = snap[r][c];
                System.out.print(v==Board.EMPTY ? " ." : (v==Board.PLAYER_1?" X":" O"));
            }
            System.out.println();
        }
    }
}
