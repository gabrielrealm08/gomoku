package org.example;

import org.example.model.Board;

public class GomokuGame {
    private final Board board;
    private int currentPlayer;

    public GomokuGame() {
        this.board = new Board();
        this.currentPlayer = Board.PLAYER_1;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public boolean makeMove(int row, int col) {
        if (!board.isValidMove(row, col)) {
            return false;
        }
        board.placeStone(row, col, currentPlayer);
        return true;
    }

    public boolean checkWin(int row, int col) {
        int winCondition = 5;

        if (countDirection(row, col, 0, 1) + countDirection(row, col, 0, -1) - 1 >= winCondition) {
            return true;
        }

        if (countDirection(row, col, 1, 0) + countDirection(row, col, -1, 0) - 1 >= winCondition) {
            return true;
        }

        if (countDirection(row, col, 1, 1) + countDirection(row, col, -1, -1) - 1 >= winCondition) {
            return true;
        }

        if (countDirection(row, col, 1, -1) + countDirection(row, col, -1, 1) - 1 >= winCondition) {
            return true;
        }

        return false;
    }

    private int countDirection(int row, int col, int rowDelta, int colDelta) {
        int count = 0;
        int currentRow = row;
        int currentCol = col;

        while (currentRow >= 0 && currentRow < Board.SIZE && 
               currentCol >= 0 && currentCol < Board.SIZE && 
               board.getCell(currentRow, currentCol) == currentPlayer) {
            count++;
            currentRow += rowDelta;
            currentCol += colDelta;
        }

        return count;
    }

    public boolean isDraw() {
        return board.isFull();
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == Board.PLAYER_1) ? Board.PLAYER_2 : Board.PLAYER_1;
    }
}