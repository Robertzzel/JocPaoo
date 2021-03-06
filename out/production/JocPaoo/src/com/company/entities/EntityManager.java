package com.company.entities;

import com.company.Handler;
import com.company.entities.Entity;
import com.company.entities.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> rederSort = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY()+a.getHeight() < b.getY()+b.getHeight())
                return -1;
            return 1;
        }
    };

    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }

    public void tick(){
        for(int i=0;i<entities.size();i++){
            entities.get(i).tick();
            if(!entities.get(i).alive){
                entities.remove(entities.get(i));
            }
        }
    }

    public void render(Graphics g){
        for(int i=0;i<entities.size();i++){
            entities.get(i).render(g);
        }

    }

    public void deleteAll(){
        for(int i=0;i<entities.size();i++){
            if(entities.get(i).getClass() == player.getClass()){
                continue;
            }else{
                entities.get(i).alive = false;
            }

        }
    }

    public void addEntity(Entity e){
        entities.add(e);
    }


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }



}
