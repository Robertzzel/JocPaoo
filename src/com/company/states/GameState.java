package com.company.states;

import com.company.Game;
import com.company.Handler;
import com.company.worlds.World;

import java.awt.*;

public class GameState extends State {
    private World world;

    public GameState(Handler handler){
        super(handler);

        handler.getDatabaseManager().restoreData();
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
        //System.out.println(Game.lvl);
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }

    public void verifEscapeKey(){
        if(handler.getKeyManager().esc){
            handler.getGame().swichState("menuState");
        }
    }
}
