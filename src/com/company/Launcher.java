package com.company;

import com.company.utils.Database;

public class Launcher {

    public static void main(String[] args) {
        //Database.createNewTable();
        Game game = Game.getInstance("Run Bob, run",1280,800);
        game.start();
    }
}
