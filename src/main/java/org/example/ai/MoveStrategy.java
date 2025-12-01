package org.example.ai;

import org.example.model.Board;
import org.example.model.Move;

public interface MoveStrategy {
    Move chooseMove(Board board, int playerId);
}
