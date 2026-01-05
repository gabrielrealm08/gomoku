package org.example.model;

/**
 * Represents a single move in the game.
 * Stores the position and which player made the move.
 */
public class Move {
    private final int row;     // Row position (0-14)
    private final int col;     // Column position (0-14)
    private final int player;  // Player who made the move (1 or 2)

    /**
     * Creates a new move.
     * @param row the row position
     * @param col the column position
     * @param player the player making the move
     */
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
