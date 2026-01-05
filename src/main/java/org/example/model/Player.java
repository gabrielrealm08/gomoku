package org.example.model;

/**
 * Represents a player in the game.
 * Each player has an ID and a name.
 */
public class Player {
    private final int id;       // Player ID (1 or 2)
    private final String name;  // Player name

    /**
     * Creates a new player.
     * @param id the player ID
     * @param name the player name
     */
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
