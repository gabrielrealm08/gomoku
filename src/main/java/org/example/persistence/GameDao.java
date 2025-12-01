package org.example.persistence;

import org.example.model.Move;

public interface GameDao {
    void saveMove(Move m);
    void startNewGame();
    void finishGame(Integer winner);
}
