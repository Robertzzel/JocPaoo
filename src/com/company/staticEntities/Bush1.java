package com.company.staticEntities;

import com.company.Handler;
import com.company.gfx.Assets;
import com.company.states.GameState;
import com.company.states.State;
import com.company.tiles.Tile;

import java.awt.*;

public class Bush1 extends StaticEntity {

    public Bush1(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 14;
        bounds.y = 23;
        bounds.width = width-30;
        bounds.height = height-20;
    }

    public Bush1(Handler handler, float x, float y, int health) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        super.health = health;
        bounds.x = 14;
        bounds.y = 23;
        bounds.width = width-30;
        bounds.height = height-20;
    }

    public void die(){
        System.out.println("BUsh DIED");
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bush1,(int)(x - handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
        g.drawRect((int)(x - handler.getGameCamera().getxOffset()+bounds.x),(int)(y-handler.getGameCamera().getyOffset()+bounds.y), bounds.width, bounds.height);
    }

}
