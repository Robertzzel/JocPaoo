package com.company.states;

import com.company.Handler;
import com.company.UI.UIManager;
import com.company.entities.Player;
import com.company.utils.Timer;
import com.company.worlds.World;

import java.awt.*;

public class GameState extends State {

    public static int lvl = 1;
    private Player player;
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
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setUiManager(handler.getGame().menuState.getUiManager());
        }
    }
}
