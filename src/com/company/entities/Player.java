package com.company.entities;

import com.company.Handler;
import com.company.gfx.Assets;
import com.company.Game;

import java.awt.*;

public class Player extends Creature {

    public Player(Handler handler, float x, float y){
        super(handler,x,y,DEFAULT_CREATURE_WIDTH,DEFAUL_CREATURE_HEIGHT);

        bounds.x = 24;//relativ la patratelul cu imginea
        bounds.y = 40;
        bounds.width = 24;
        bounds.height = 24;
    }

    @Override
    public void tick() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player,(int)(x - handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
        g.setColor(Color.red);
        g.fillRect((int)((x+bounds.x - handler.getGameCamera().getxOffset())),
                (int)((y+bounds.y - handler.getGameCamera().getyOffset())),
                bounds.width, bounds.height);
    }
}
