package com.company.utils;
import com.company.Game;

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

            Game.lvl = lvl;
            Game.secRamase = secRamase;
            Game.killedMobs = tufeDistruse;
        }

    }

    public void insertData(String nume){
        database.insert(nume);
    }

    public void printData(){
        database.printAll();
    }

    public void deleteData(){
        database.dropTable();
    }
}
