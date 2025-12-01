package org.example.ui;

import org.example.ai.RandomStrategy;
import org.example.model.Board;
import org.example.persistence.JdbcGameDao;
import org.example.service.GameService;

public class GomokuApp {
    public static void main(String[] args) {
        Board board = new Board();
        // Use H2 in-memory database by default; change to file-based URL to persist between runs
        String jdbcUrl = "jdbc:h2:./gomoku_db;AUTO_SERVER=FALSE"; // file DB in project folder
        JdbcGameDao dao = new JdbcGameDao(jdbcUrl);
        GameService service = new GameService(board, dao);
        ConsoleUI ui = new ConsoleUI(service, new RandomStrategy());
        ui.start();
        dao.finishGame(null);
    }
}
