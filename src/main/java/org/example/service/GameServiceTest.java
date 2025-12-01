package org.example.service;

import org.example.model.Board;
import org.example.model.Move;
import org.example.persistence.GameDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDao implements GameDao {
    @Override public void saveMove(Move m) {}
    @Override public void startNewGame() {}
    @Override public void finishGame(Integer winner) {}
}

public class GameServiceTest {
    private GameService service;

    @BeforeEach
    void setUp() {
        service = new GameService(new Board(), new InMemoryDao());
    }

    @Test
    void horizontalWin() {
        int r = 7; // 0-based
        for (int c = 3; c <= 7; c++) {
            assertTrue(service.makeMove(r, c));
            if (c < 7) service.switchPlayer(); // alternate to simulate other player's placement but we want same player -> so set player back
            // set same player again for simplicity
            service = resetToPlayer(service, Board.PLAYER_1);
        }
        assertTrue(service.checkWin(r, 7));
    }

    @Test
    void invalidMoveRejected() {
        assertTrue(service.makeMove(0,0));
        assertFalse(service.makeMove(0,0)); // occupied
    }

    @Test
    void drawDetection() {
        Board b = new Board();
        GameService s = new GameService(b, new InMemoryDao());
        // fill board quickly with alternating players
        int p = Board.PLAYER_1;
        for (int r=0;r<Board.SIZE;r++){
            for (int c=0;c<Board.SIZE;c++){
                s = resetToPlayer(s, p);
                s.makeMove(r,c);
                p = (p==Board.PLAYER_1)?Board.PLAYER_2:Board.PLAYER_1;
            }
        }
        assertTrue(s.isDraw());
    }

    // helper to force service current player (test convenience)
    private GameService resetToPlayer(GameService s, int player) {
        while (s.getCurrentPlayer() != player) s.switchPlayer();
        return s;
    }
}
