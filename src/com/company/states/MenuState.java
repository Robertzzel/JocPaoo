package com.company.states;

import com.company.Game;
import com.company.Handler;

import java.awt.*;

public class MenuState extends State {

    public MenuState(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {
        if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed())
            State.setState(handler.getGame().gameState);
    }

    @Override
    public void render(Graphics g) {

    }
}
