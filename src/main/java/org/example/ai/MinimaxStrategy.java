package org.example.ai;

import org.example.model.Board;
import org.example.model.Move;

// Minimal stub: included to demonstrate Strategy pattern. Replace with proper implementation if needed.
public class MinimaxStrategy implements MoveStrategy {
    @Override
    public Move chooseMove(Board board, int playerId) {
        // naive: choose centre or first free cell
        int center = Board.SIZE/2;
        if (board.get(center, center) == Board.EMPTY) return new Move(center, center, playerId);
        for (int r=0;r<Board.SIZE;r++) for (int c=0;c<Board.SIZE;c++) if (board.get(r,c)==Board.EMPTY) return new Move(r,c,playerId);
        return null;
    }
}
