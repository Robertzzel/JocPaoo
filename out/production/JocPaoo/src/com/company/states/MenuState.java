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
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(565,366,150,64, Assets.helpBtn, new ClickListener(){ //HELP
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(handler.getGame().helpState.getUiManager());
                State.setState(handler.getGame().helpState);
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

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }
}
