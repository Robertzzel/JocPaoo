package com.company.entities;

import com.company.gfx.Assets;
import com.company.Game;

import java.awt.*;

public class Player extends Creature {

    public Player(Game game, float x, float y){
        super(game,x,y,DEFAULT_CREATURE_WIDTH,DEFAUL_CREATURE_HEIGHT);
    }

    @Override
    public void tick() {
        getInput();
        move();
        game.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(game.getKeyManager().up)
            yMove = -speed;
        if(game.getKeyManager().down)
            yMove = speed;
        if(game.getKeyManager().left)
            xMove = -speed;
        if(game.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player,(int)(x - game.getGameCamera().getxOffset()),(int)(y-game.getGameCamera().getyOffset()),width,height,null);
    }
}
