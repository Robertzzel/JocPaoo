package com.company.states;

import com.company.Handler;
import com.company.UI.UIManager;
import com.company.entities.Player;
import com.company.worlds.World;

import java.awt.*;

public class GameState extends State {

    public static int lvl = 1;
    private Player player;
    private World world;
    private int min=3,sec=0;
    private long currentTime=System.currentTimeMillis();

    public GameState(Handler handler){
        super(handler);
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);

    }

    public void clock(){
        if(System.currentTimeMillis() - currentTime > 1000){
            if(sec+min>0){
                if(sec == 0 && min > 0){
                    min--;
                    sec=59;
                }else{
                    sec--;
                }
                handler.getGame().getDispaly().mesaj.setText(min+":"+sec);
                System.out.println("schimbare");
            }else{
                this.min=2;
                this.sec=59;
                State.setState(handler.getGame().menuState);
                handler.getMouseManager().setUiManager(handler.getGame().uiManagerMenu);

            }
            currentTime = System.currentTimeMillis();
        }

    }

    @Override
    public void tick() {
        world.tick();
        clock();
        verifEscapeKey();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }

    public void verifEscapeKey(){
        if(handler.getKeyManager().esc){
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setUiManager(handler.getGame().uiManagerMenu);
        }
    }
}
