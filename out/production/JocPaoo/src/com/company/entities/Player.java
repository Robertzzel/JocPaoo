package com.company.entities;

import com.company.Handler;
import com.company.gfx.Animation;
import com.company.gfx.Assets;
import com.company.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    private Animation animaDown, animaUp, animaLeft, animaRight;
    private long lastAttackTimer, attackCooldown = 500,attackTimer=attackCooldown;
    private int speed;

    public Player(Handler handler, float x, float y, int speed){
        super(handler,x,y,48,48);

        this.speed = speed;
        bounds.x = 14;//relativ la patratelul cu imginea
        bounds.y = 29;
        bounds.width = 24;
        bounds.height = 24;

        animaDown = new Animation(100,Assets.player_down);
        animaUp = new Animation(100,Assets.player_up);
        animaLeft = new Animation(100,Assets.player_left);
        animaRight = new Animation(100,Assets.player_right);

    }

    public void die(){
        System.out.println("GAME OVER");
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

        checkAttack();
    }

    public void checkAttack(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown){
            return;
        }

        Rectangle cb= getCollisionBounds(0,0);
        Rectangle aR = new Rectangle();
        int arSize = 30;
        aR.width = arSize;
        aR.height = arSize;

        if(handler.getKeyManager().attack){

            if(handler.getKeyManager().up){
                aR.x = cb.x + cb.width/2 - arSize/2;
                aR.y = cb.y - arSize;
            }else if(handler.getKeyManager().down){
                aR.x = cb.x + cb.width/2 - arSize/2;
                aR.y = cb.y + cb.height;
            }else if(handler.getKeyManager().left){
                aR.x = cb.x - arSize;
                aR.y = cb.y + cb.height/2 - arSize/2;
            }else if(handler.getKeyManager().right){
                aR.x = cb.x + cb.width;
                aR.y = cb.y + cb.height/2 - arSize/2;
            }else{
                return;
            }

            attackTimer = 0;

            for(Entity e: handler.getWorld().getEntityManager().getEntities()){
                if(e.equals(this))
                    continue;
                if(e.getCollisionBounds(0,0).intersects(aR)){
                    e.hurt(1);
                    return;
                }
            }

        }
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
        //       (int)((y+bounds.y - handler.getGameCamera().getyOffset())),
        //        bounds.width, bounds.height);
    }

    private BufferedImage getCurrendAnimationFrame(){
        if(yMove < 0){
            return animaUp.getCurrentFrame();
        }else if(xMove >0){
            return animaRight.getCurrentFrame();
        }else if(xMove < 0){
            return animaLeft.getCurrentFrame();
        }else if(yMove > 0){
            return animaDown.getCurrentFrame();
        }else{
            return Assets.player_still;
        }
    }
}
