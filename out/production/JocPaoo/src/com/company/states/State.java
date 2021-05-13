package com.company.states;

import com.company.Game;
import com.company.Handler;
import com.company.UI.UIManager;

import java.awt.*;

public abstract class State {

    private static State currentState = null;
    private UIManager uiManager;

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){ return currentState;}


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
