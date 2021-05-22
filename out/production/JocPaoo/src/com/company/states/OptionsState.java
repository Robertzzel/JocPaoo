package com.company.states;

import com.company.Handler;
import com.company.UI.ClickListener;
import com.company.UI.UIImageButton;
import com.company.UI.UIManager;
import com.company.gfx.Assets;

import java.awt.*;

public class OptionsState extends State{

    UIManager uiManager;
    public static int music=0,easy=0;

    public OptionsState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(565, 250, 150, 64, Assets.onBtn, new ClickListener() {//ON MUSIC
            @Override
            public void onClick() {
                music = 1;
                handler.getGame().getDbManager().insertData("player");
            }
        }));

        uiManager.addObject(new UIImageButton(720, 250, 150, 64, Assets.offBtn, new ClickListener() {//OFF MUSIC
            @Override
            public void onClick() {
                music = 0;
                handler.getGame().getDbManager().insertData("player");
            }
        }));

        uiManager.addObject(new UIImageButton(565, 344, 150, 64, Assets.onBtn, new ClickListener() {//ON EASY
            @Override
            public void onClick() {
                easy = 1;
                handler.getWorld().getEntityManager().getPlayer().setSpeed(4);
                handler.getGame().getDbManager().insertData();
            }
        }));

        uiManager.addObject(new UIImageButton(565, 438, 150, 64, Assets.onBtn, new ClickListener() {//ON HARD
            @Override
            public void onClick() {
                easy = 0;
                handler.getWorld().getEntityManager().getPlayer().setSpeed(2);
                handler.getGame().getDbManager().insertData();
            }
        }));
    }

    public void verifEscapeKey(){
        if(handler.getKeyManager().esc){
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setUiManager(handler.getGame().menuState.getUiManager());
        }
    }

    public void drawOptions(Graphics g){
        g.setFont(Assets.fontSubtitle);
        g.setColor(Assets.oliveGreen);
        g.drawString("Music:",450,300);
        g.drawString("Easy Mode:",380,386);
        g.drawString("Hard Mode:",380,480);
    }

    @Override
    public void tick() {
        uiManager.tick();
        verifEscapeKey();
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        drawOptions(g);
        uiManager.render(g);

    }

    public void drawBackground(Graphics g){
        g.setColor(Assets.pewter);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
    }

    @Override
    public UIManager getUiManager() {
        return uiManager;
    }
}
