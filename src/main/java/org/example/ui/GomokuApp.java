package org.example.ui;

import org.example.GomokuGame;

public class GomokuApp {
    public static void main(String[] args) {
        GomokuGame game = new GomokuGame();
        ConsoleUI ui = new ConsoleUI(game);
        ui.start();
    }
}
