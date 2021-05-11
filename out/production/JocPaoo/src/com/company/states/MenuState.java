package com.company.states;

import com.company.Game;
import com.company.Handler;
import com.company.gfx.Assets;

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
        g.drawImage(Assets.startBtn[0],0,0,null);
        g.drawImage(Assets.startBtn[1],0,80,null);
    }
}
