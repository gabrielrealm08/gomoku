package org.example.persistence;

import org.example.model.Move;

import java.sql.*;

public class JdbcGameDao implements GameDao {
    private final String url;
    private Connection conn;
    private long currentGameId = -1;
    private int moveIndex = 0;

    public JdbcGameDao(String jdbcUrl) {
        this.url = jdbcUrl;
        try {
            conn = DriverManager.getConnection(url);
            // ensure schema exists; assume resources/schema.sql was executed by user or add exec here
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void startNewGame() {
        try (PreparedStatement st = conn.prepareStatement("INSERT INTO games(started_at) VALUES (CURRENT_TIMESTAMP)", Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) currentGameId = rs.getLong(1);
            moveIndex = 0;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public void saveMove(Move m) {
        if (currentGameId == -1) startNewGame();
        try (PreparedStatement st = conn.prepareStatement("INSERT INTO moves(game_id, move_index, row, col, player) VALUES (?,?,?,?,?)")) {
            st.setLong(1, currentGameId);
            st.setInt(2, ++moveIndex);
            st.setInt(3, m.getRow());
            st.setInt(4, m.getCol());
            st.setInt(5, m.getPlayer());
            st.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public void finishGame(Integer winner) {
        if (currentGameId == -1) return;
        try (PreparedStatement st = conn.prepareStatement("UPDATE games SET finished_at=CURRENT_TIMESTAMP, winner=? WHERE id=?")) {
            if (winner == null) st.setNull(1, Types.INTEGER); else st.setInt(1, winner);
            st.setLong(2, currentGameId);
            st.executeUpdate();
            currentGameId = -1;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}
