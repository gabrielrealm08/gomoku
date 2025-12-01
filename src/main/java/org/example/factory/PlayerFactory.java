package org.example.factory;

import org.example.model.Player;

public class PlayerFactory {
    public static Player human(int id, String name) { return new Player(id, name); }
    public static Player ai(int id) { return new Player(id, "AI-" + id); }
}
