package com.company.states;

import com.company.Game;
import com.company.Handler;
import com.company.utils.AudioPlayer;
import com.company.worlds.World;

import java.awt.*;

public class GameState extends State {
    private World world;

    public GameState(Handler handler){
        super(handler);

        if (Game.lvl>=4)
            Game.resetScore();

        world = new World(handler,"res/worlds/world"+ Game.lvl +".txt");
        handler.setWorld(world);

        handler.getWorld().getEntityManager().getPlayer().setX(handler.getWorld().getSpawnX());
        handler.getWorld().getEntityManager().getPlayer().setY(handler.getWorld().getSpawnY());
    }

    @Override
    public void tick() {
        world.tick();
        verifEscapeKey();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }

    public void verifEscapeKey(){
        if(handler.getKeyManager().esc){
            handler.getDatabaseManager().insertData();
            handler.getGame().swichState("menuState");
        }
    }
}
