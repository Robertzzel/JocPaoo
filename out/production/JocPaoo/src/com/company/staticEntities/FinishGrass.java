package com.company.staticEntities;

import com.company.Game;
import com.company.Handler;
import com.company.gfx.Assets;
import com.company.states.State;
import com.company.tiles.Tile;

import java.awt.*;

public class FinishGrass extends  StaticEntity{
    public FinishGrass(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 10;
        bounds.y = (int) (height/1.5);
        bounds.width = width-20;
        bounds.height = (int) (height-height/1.5);
    }


    public void nextLevel(){
        if(this.checkPlayerCollisions(0,10)){
            Game.lvl++;
            if(Game.lvl >= 4){
                handler.getGame().swichState("finishState");
                Game.lvl = 1;
                handler.getWorld().setWorld();
            }else {
                Game.secRamase += handler.getWorld().getTimer().getRemainingSeconds();
                handler.getWorld().setWorld();
                handler.getGame().getDbManager().insertData("player",Game.lvl,Game.secRamase,Game.killedMobs);
            }
        }
    }

    @Override
    public void tick() {
        nextLevel();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.grass,(int)(x - handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
    }
}
