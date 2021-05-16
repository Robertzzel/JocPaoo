package com.company.staticEntities;

import com.company.Game;
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


    public void nextLevel(){
        if(this.checkPlayerCollisions(0,10)){
            Game.lvl++;


            if(Game.lvl >= 4){
                State.setState(handler.getGame().finishState);
                handler.getGame().getMouseManager().setUiManager(handler.getGame().finishState.getUiManager());
                handler.getGame().getDispaly().mesaj.setBackground(Assets.pewter);
            }else {
                Game.secRamase += handler.getWorld().getTimer().getRemainingSeconds();
                handler.getWorld().getEntityManager().deleteAll();
                handler.getWorld().loadWorld("res/worlds/world" + Game.lvl + ".txt");
                handler.getGame().getDispaly().mesaj.setBackground(new Color(25, 117, 84));
            }
            System.out.println("Se incarca "+Game.lvl + " "+ Game.secRamase+" "+Game.killedMobs);
            handler.getGame().getDbManager().insertData("player",Game.lvl,Game.secRamase,Game.killedMobs);

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
