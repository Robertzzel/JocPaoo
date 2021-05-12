package com.company.staticEntities;

import com.company.Handler;
import com.company.gfx.Assets;
import com.company.states.State;
import com.company.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 10;
        bounds.y = (int) (height/1.5);
        bounds.width = width-20;
        bounds.height = (int) (height-height/1.5);
    }

    @Override
    public void tick() {
        if(this.checkEntityCollisions(0,10)){
            State.setState(handler.getGame().menuState);
            handler.getGame().getMouseManager().setUiManager(handler.getGame().uiManagerMenu);
            handler.getWorld().getEntityManager().getPlayer().setX(handler.getWorld().getSpawnx());
            handler.getWorld().getEntityManager().getPlayer().setY(handler.getWorld().getSpawny());
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree,(int)(x - handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
    }
}
