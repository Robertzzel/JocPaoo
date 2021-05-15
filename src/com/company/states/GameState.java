package com.company.states;

import com.company.Handler;
import com.company.worlds.World;

import java.awt.*;

public class GameState extends State {

    public static int lvl = 1;
    public static int secRamase = 0;
    public static int killedMobs = 0;
    private World world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);
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
            swichState("menuState");
        }
    }
}
