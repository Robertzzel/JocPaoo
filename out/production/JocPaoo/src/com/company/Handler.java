package com.company;

import com.company.gfx.GameCamera;
import com.company.input.KeyManager;
import com.company.input.MouseManager;
import com.company.utils.DatabaseManager;
import com.company.worlds.World;

public class Handler {

    private Game game;
    private World world;

    public Handler(Game game){
        this.game = game;
    }
    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public DatabaseManager getDatabaseManager(){return game.getDbManager();}

    public int getWidth(){
        return  game.getWidth();
    }

    public int getHeight(){
        return  game.getHeight();
    }

    public Game getGame(){
        return game;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public World getWorld(){
        return world;
    }

    public void setWorld(World world){
        this.world = world;
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }
}
