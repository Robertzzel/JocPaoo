package com.company.worlds;

import com.company.Handler;
import com.company.entities.EntityManager;
import com.company.entities.Player;
import com.company.staticEntities.Tree;
import com.company.tiles.Tile;
import com.company.utils.Utils;

import java.awt.*;

public class World {
    private Handler handler;
    private int width, height;  //marimea hartii
    private int spawnX, spawnY;  //punctul de spanw pentru player

    private int[][] tiles;  //matrice pentru harta

    //Entitites
    private EntityManager entityManager;


    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler,new Player(handler,100,100));
        entityManager.addEntity(new Tree(handler,570,10));

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }
    public void tick()
    {
        entityManager.tick(); //tick la toate entitatile
    }
    public void render (Graphics g)
    {
        int xStart = (int) Math.max(0,handler.getGameCamera().getxOffset() / Tile.TILEWIDTH); //marginile dalelor care se vad pe ecran (singurele care ar trebui randate, deoarece doar ele se vad pe ecran)
        int xEnd=(int)Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart =(int) Math.max(0,handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd=(int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y=yStart;y<yEnd;y++)
            for(int x =xStart;x<xEnd;x++)
            {
                getTile(x,y).render(g,(int)(x*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int)(y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset())); //x si y sunt unitati de tiles si trebuie transformate in unitati de ecran

            }
        //Entities
        entityManager.render(g);
    }
    public Tile getTile(int x, int y)
    {
        if(x <0 || y < 0 || x>=width || y >= height) //te asigura ca nu ai iesit din mapa
            return Tile.grassTile;

        Tile t = Tile.tiles[tiles[x][y]]; //array din clasa Tile
        if( t == null)
            return Tile.grassTile;
        return t;
    }
    public void loadWorld(String path) {
        String file = Utils.loadFileAsString(path); //clasa declarata in utils si contine metoda ajutatoare pentru program
        //loadFileAsString incarca continutul din fisierul dat la path

        String[] tokens = file.split("\\s+");    //split la oricat de multe spatii albe

        width = Utils.parseInt(tokens[0]); //marimea hartii
        height = Utils.parseInt(tokens[1]);

        spawnX = Utils.parseInt(tokens[2]); //punctul de spanw pentru player
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y = 0;y<height;y++)
            for(int x=0;x<width;x++)
            {
                tiles[x][y] = Utils.parseInt(tokens[(x+y * width) + 4 ]);
            }
    }
    public int getWidth(){
        return width;
    }
    public int getHeight()
    {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }
}