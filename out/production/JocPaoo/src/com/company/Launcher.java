package com.company;

import com.company.utils.Database;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("Run Bob, run",1280,800);
        game.start();
    }
}
