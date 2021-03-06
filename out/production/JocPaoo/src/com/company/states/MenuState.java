package com.company.states;

import com.company.Game;
import com.company.Handler;
import com.company.UI.ClickListener;
import com.company.UI.UIImageButton;
import com.company.UI.UIManager;
import com.company.gfx.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        this.uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(565, 300, 150, 64, Assets.newBtn, new ClickListener() {//NEW
            @Override
            public void onClick() {
                Game.resetScore();
                handler.getWorld().setWorld();
                handler.getGame().swichState("gameState");
            }
        }));

        uiManager.addObject(new UIImageButton(565, 366, 150, 64, Assets.continueBtn, new ClickListener() {//CONTINUE
            @Override
            public void onClick() {
                handler.getDatabaseManager().restoreData();
                handler.getWorld().setWorld();
                handler.getGame().swichState("gameState");
            }
        }));
        uiManager.addObject(new UIImageButton(565, 432, 150, 64, Assets.optionsBtn, new ClickListener() {//CONTINUE
            @Override
            public void onClick() {
                handler.getGame().swichState("optionsState");
            }
        }));

        uiManager.addObject(new UIImageButton(565,498,150,64, Assets.helpBtn, new ClickListener(){ //HELP
            @Override
            public void onClick() {
                handler.getGame().swichState("helpState");
            }
        }));

        uiManager.addObject(new UIImageButton(565,564,150,64, Assets.exitBtn, new ClickListener(){ //EXIT
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                handler.getGame().getDispaly().getFrame().dispatchEvent(new WindowEvent(handler.getGame().getDispaly().getFrame(), WindowEvent.WINDOW_CLOSING));
            }
        }));
    }

    private void drawTitlu(Graphics g,int x,int y){

        g.setColor(Assets.green);
        g.setFont(Assets.fontTitle);
        g.drawString("Run Bob, run", x, y);

    }

    public void drawBackground(Graphics g){
        g.setColor(Assets.pewter);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        drawTitlu(g,handler.getWidth()/2-150,100);
        uiManager.render(g);
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }
}
