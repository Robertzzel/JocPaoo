package com.company.utils;
import com.company.Game;
import com.company.states.OptionsState;

public class DatabaseManager {

    private Database database = new Database();

    public DatabaseManager(){
        Database.createNewTable();
    }

    public void restoreData(){
        if (database.selectAll() != null) {
            String[] date=database.selectAll().split(" ");
            String nume = date[0];
            int lvl = Integer.parseInt(date[1]);
            int secRamase = Integer.parseInt(date[2]);
            int tufeDistruse = Integer.parseInt(date[3]);
            int music = Integer.parseInt(date[4]);
            int easy = Integer.parseInt(date[5]);

            Game.lvl = lvl;
            Game.secRamase = secRamase;
            Game.killedMobs = tufeDistruse;
            OptionsState.music = music;
            OptionsState.easy = easy;
        }

    }

    public String getHarta(){
        return database.getHarta();
    }

    public void insertData(String nume){
        database.insert(nume);
    }

    public void insertData(){
        database.insert();
    }

//    public void printData(){
//        database.printAll();
//    }
}
