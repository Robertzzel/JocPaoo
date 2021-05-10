package com.company.entities;

import com.company.Handler;
import com.company.gfx.Animation;
import com.company.gfx.Assets;
import com.company.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    private Animation animaDown, animaUp, animaLeft, animaRight;

    public Player(Handler handler, float x, float y){
        super(handler,x,y,DEFAULT_CREATURE_WIDTH,DEFAUL_CREATURE_HEIGHT);

        bounds.x = 17;//relativ la patratelul cu imginea
        bounds.y = 40;
        bounds.width = 24;
        bounds.height = 24;

        animaDown = new Animation(300,Assets.player_down);
        animaUp = new Animation(300,Assets.player_up);
        animaLeft = new Animation(300,Assets.player_left);
        animaRight = new Animation(300,Assets.player_right);

    }

    @Override
    public void tick() {
        animaDown.tick();
        animaLeft.tick();
        animaRight.tick();
        animaUp.tick();
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
        g.drawImage(getCurrendAnimationFrame(),(int)(x - handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
        g.setColor(Color.red);
        //g.fillRect((int)((x+bounds.x - handler.getGameCamera().getxOffset())),
          //      (int)((y+bounds.y - handler.getGameCamera().getyOffset())),
            //    bounds.width, bounds.height);
    }

    private BufferedImage getCurrendAnimationFrame(){
        if(xMove < 0){
            return animaLeft.getCurrentFrame();
        }else if(xMove >0){
            return animaRight.getCurrentFrame();
        }else if(yMove < 0){
            return animaUp.getCurrentFrame();
        }else{
            return animaDown.getCurrentFrame();
        }
    }
}
