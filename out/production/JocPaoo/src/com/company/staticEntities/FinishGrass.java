package com.company.staticEntities;

import com.company.Handler;
import com.company.gfx.Assets;
import com.company.states.GameState;
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

    public void die(){
        System.out.println("TREE DIED");
    }

    public void nextLevel(){
        if(this.checkPlayerCollisions(0,10)){
            GameState.lvl++;
            System.out.println("Nivelul:"+GameState.lvl);

            if(GameState.lvl == 4){
                State.setState(handler.getGame().menuState);
                handler.getGame().getMouseManager().setUiManager(handler.getGame().menuState.getUiManager());
                GameState.lvl = 1;
                return;
            }


            handler.getWorld().getEntityManager().deleteAll();
            handler.getWorld().loadWorld("res/worlds/world" + GameState.lvl + ".txt");
            System.out.println("Incarcare mapa "+ GameState.lvl);
            handler.getWorld().getEntityManager().getPlayer().setX(handler.getWorld().getSpawnX());
            handler.getWorld().getEntityManager().getPlayer().setY(handler.getWorld().getSpawnY());
        }
    }

    @Override
    public void tick() {
        nextLevel();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.grass,(int)(x - handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
        //g.drawRect((int)(x - handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),10,10);
    }
}
