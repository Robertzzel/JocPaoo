package com.company.states;

import com.company.Game;
import com.company.Handler;
import com.company.UI.UIManager;
import com.company.gfx.Assets;

import java.awt.*;

public abstract class State {

    private static State currentState = null;
    private UIManager uiManager;

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){ return currentState;}

    public void swichState(String state) {
        if (state == "gameState") {
            handler.getGame().getDispaly().mesaj.setBackground(new Color(25, 117, 84));
            handler.getMouseManager().setUiManager(null);
            State.setState(handler.getGame().gameState);
        } else if (state == "menuState") {
            handler.getMouseManager().setUiManager(handler.getGame().menuState.getUiManager());
            State.setState(handler.getGame().menuState);
            handler.getGame().getDispaly().mesaj.setBackground(Assets.pewter);
        } else if (state == "helpState") {
            handler.getMouseManager().setUiManager(handler.getGame().helpState.getUiManager());
            State.setState(handler.getGame().helpState);
        } else if (state == "finishState") {
            handler.getMouseManager().setUiManager(handler.getGame().finishState.getUiManager());
            State.setState(handler.getGame().finishState);
            handler.getGame().getDispaly().mesaj.setBackground(Assets.pewter);
        } else if (state == "loseState") {
            System.out.println("Lose");
        } else {
        }
    }

    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public UIManager getUiManager() {
        return uiManager;
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }
}
