package com.company.staticEntities;

import com.company.Handler;
import com.company.gfx.Assets;
import com.company.tiles.Tile;

import java.awt.*;


public class Bush2 extends StaticEntity{

    public Bush2(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 14;
        bounds.y = 23;
        bounds.width = width-30;
        bounds.height = height-20;
    }

    public Bush2(Handler handler, float x, float y, int health) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        super.health = health;
        bounds.x = 0;
        bounds.y = 23;
        bounds.width = width;
        bounds.height = height-20;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bush2,(int)(x - handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
    }

}
