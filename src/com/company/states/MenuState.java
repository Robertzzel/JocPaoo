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

        uiManager.addObject(new UIImageButton(565,300,150,64, Assets.startBtn, new ClickListener(){ //START
            @Override
            public void onClick() {
                swichState("gameState");
            }
        }));

        uiManager.addObject(new UIImageButton(565,366,150,64, Assets.helpBtn, new ClickListener(){ //HELP
            @Override
            public void onClick() {
                swichState("helpState");
            }
        }));

        uiManager.addObject(new UIImageButton(565,430,150,64, Assets.exitBtn, new ClickListener(){ //EXIT
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                handler.getGame().getDispaly().getFrame().dispatchEvent(new WindowEvent(handler.getGame().getDispaly().getFrame(), WindowEvent.WINDOW_CLOSING));
            }
        }));
    }

    public void drawBackground(Graphics g){
        g.setColor(Assets.pewter);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
    }

    @Override
    public void tick() {
        uiManager.tick();
        System.out.println(Game.lvl);
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        uiManager.render(g);
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }
}
