package org.example.ai;

import org.example.model.Board;
import org.example.model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStrategy implements MoveStrategy {
    private final Random rnd = new Random();

    @Override
    public Move chooseMove(Board board, int playerId) {
        List<int[]> free = new ArrayList<>();
        for (int r = 0; r < Board.SIZE; r++) for (int c = 0; c < Board.SIZE; c++) if (board.get(r, c) == Board.EMPTY) free.add(new int[]{r, c});
        if (free.isEmpty()) return null;
        int[] pick = free.get(rnd.nextInt(free.size()));
        return new Move(pick[0], pick[1], playerId);
    }
}
