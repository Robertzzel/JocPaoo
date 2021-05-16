package com.company.utils;

import com.company.Game;
import com.company.states.GameState;

public class DatabaseManager {

    private Database database = new Database();

    public DatabaseManager(){
        database.createNewTable();
    }

    public void restoreData(){
        String[] date=database.selectAll().split(" ");
        String nume = date[0];
        int lvl = Integer.parseInt(date[1]);
        int secRamase = Integer.parseInt(date[2]);
        int tufeDistruse = Integer.parseInt(date[3]);

        Game.lvl = lvl;
        Game.secRamase = secRamase;
        Game.killedMobs = tufeDistruse;


    }

    public void insertData(String nume,int lvl,int secRamase,int tufeDistruse){
        database.insert(nume,lvl,secRamase,tufeDistruse);
    }
}
