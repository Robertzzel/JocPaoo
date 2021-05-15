package com.company.entities;

import com.company.Game;
import com.company.Handler;
import com.company.states.GameState;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    protected int health;
    protected boolean alive = true;
    public static final int DEFAULT_HEALTH = 10;
    protected float x,y;
    protected int width, height;
    protected Rectangle bounds;

    public Entity(Handler handler,float x , float y,int width , int height){
        this.handler =  handler;
        this.x = x;
        this.y = y;
        this.health = DEFAULT_HEALTH;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0,0,width,height);
    }

    public void hurt(int dmg){
        health -= dmg;
        if(health<=0){
            alive = false;
            die();
        }
    }

    public static void die(){
        GameState.killedMobs++;
    }
    public abstract void tick();
    public abstract void render(Graphics g);

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int)(x+bounds.x+xOffset),(int)(y+bounds.y+yOffset),bounds.width,bounds.height);
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e: handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset)))
                return true;
        }
        return false;
    }

    public boolean checkPlayerCollisions(float xOffset, float yOffset){
        for(Entity e: handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset)) && e.getClass() == handler.getWorld().getEntityManager().getPlayer().getClass())
                return true;
        }
        return false;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
