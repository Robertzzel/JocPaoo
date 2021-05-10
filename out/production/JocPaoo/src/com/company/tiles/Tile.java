package com.company.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    //STATIC

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile rockTIle = new RockTile(1);
    public static Tile treeTIle = new TreeTile(2);
    public static Tile bradTile = new BradTile(3);
    public static Tile solidGrassTile = new SolidGrassTile(4);


    //CLASS
    public static final int TILEWIDTH=64,TILEHEIGHT=64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture,int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture,x,y,TILEWIDTH,TILEHEIGHT,null);
    }

    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }
}
