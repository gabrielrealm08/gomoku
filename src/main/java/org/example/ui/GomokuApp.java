package org.example.ui;

import org.example.GomokuGame;

/**
 * Main application entry point for Gomoku.
 * Creates and starts a new game.
 */
public class GomokuApp {
    /**
     * Main method to start the game.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a new game
        GomokuGame game = new GomokuGame();
        
        // Create the console UI
        ConsoleUI ui = new ConsoleUI(game);
        
        // Start the game
        ui.start();
    }
}
