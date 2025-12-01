package org.example.service;

import org.example.model.Board;
import org.example.model.Move;
import org.example.persistence.GameDao;

import java.util.Objects;

public class GameService {
    private final Board board;
    private final GameDao gameDao; // DAO pattern

    private int currentPlayer;

    public GameService(Board board, GameDao gameDao) {
        this.board = Objects.requireNonNull(board);
        this.gameDao = Objects.requireNonNull(gameDao);
        this.currentPlayer = Board.PLAYER_1;
    }

    public int getCurrentPlayer() { return currentPlayer; }

    public synchronized boolean makeMove(int row, int col) {
        if (!board.isValidMove(row, col)) return false;
        Move m = new Move(row, col, currentPlayer);
        board.placeMove(m);
        gameDao.saveMove(m); // persist move immediately
        return true;
    }

    public synchronized boolean checkWin(int row, int col) {
        int need = 5;
        if (countConsecutive(row, col, 0, 1) + countConsecutive(row, col, 0, -1) - 1 >= need) return true;
        if (countConsecutive(row, col, 1, 0) + countConsecutive(row, col, -1, 0) - 1 >= need) return true;
        if (countConsecutive(row, col, 1, 1) + countConsecutive(row, col, -1, -1) - 1 >= need) return true;
        if (countConsecutive(row, col, 1, -1) + countConsecutive(row, col, -1, 1) - 1 >= need) return true;
        return false;
    }

    private int countConsecutive(int r, int c, int dr, int dc) {
        int count = 0;
        int player = board.get(r, c);
        int tr = r, tc = c;
        while (tr >= 0 && tr < Board.SIZE && tc >= 0 && tc < Board.SIZE && board.get(tr, tc) == player) {
            count++; tr += dr; tc += dc;
        }
        return count;
    }

    public synchronized boolean isDraw() {
        return board.isFull();
    }

    public synchronized void switchPlayer() {
        currentPlayer = (currentPlayer == Board.PLAYER_1) ? Board.PLAYER_2 : Board.PLAYER_1;
    }

    public Board getBoard() { return board; }
}
