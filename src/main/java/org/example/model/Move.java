package org.example.model;

public class Move {
    private final int row;
    private final int col;
    private final int player;

    public Move(int row, int col, int player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getPlayer() {
        return player;
    }
}
