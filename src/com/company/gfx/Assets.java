package com.company.gfx;

import com.company.gfx.ImageLoader;
import com.company.gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static final int width = 32 , height = 32;
    public static final int DEFAULT_TILE_WIDTH=64,DEFAULT_TILE_HEIGHT=64;
    public static BufferedImage grass, stone, tree, brad, boat, player_still;
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage startBtn[], helpBtn[], exitBtn[], continueBtn[], newBtn[], onBtn[], offBtn[], optionsBtn[];
    public static BufferedImage bush1,bush2,treeEntity;
    public static Font fontSubtitle = new Font("Comic Sans MS", Font.BOLD, 25);
    public static Font fontDetails = new Font("Comic Sans MS", Font.BOLD, 15);
    public static Font fontTitle = new Font("Comic Sans MS", Font.BOLD, 40);
    public static Color pewter = new Color(12, 45, 72),
                        green = new Color(20,93,160),
                        olive = new Color(46,139,192),
                        oliveGreen = new Color(177,212,224);

    public static BufferedImage winDog,loseDog;

    public static void init(){

        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        SpriteSheet worldSheet = new SpriteSheet(ImageLoader.loadImage("/textures/world.png"));
        SpriteSheet btnSheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheetButoane.png"));
        SpriteSheet entitysSheet = new SpriteSheet(ImageLoader.loadImage("/textures/worldTra.png"));

        //DOGS
        winDog = ImageLoader.loadImage("/textures/winDog.png");
        loseDog = ImageLoader.loadImage("/textures/loseDog.png");

        //Enitityes
        treeEntity = entitysSheet.crop(10*16,3*16,3*16,3*16);
        bush1 = entitysSheet.crop(8*16,4*16,16,16);
        bush2 = entitysSheet.crop(8*16,8*16,16,16);

        //BUTTONS
        startBtn = new BufferedImage[2];
        startBtn[0] = btnSheet.crop(0,192,128,48);
        startBtn[1] = btnSheet.crop(0,240,128,48);

        helpBtn = new BufferedImage[2];
        helpBtn[0] = btnSheet.crop(0,96,128,48);
        helpBtn[1] = btnSheet.crop(0,144,128,48);

        exitBtn = new BufferedImage[2];
        exitBtn[1] = btnSheet.crop(0,0,128,48);
        exitBtn[0] = btnSheet.crop(0,48,128,48);

        continueBtn = new BufferedImage[2];
        continueBtn[0] = btnSheet.crop(128,0,128,48);
        continueBtn[1] = btnSheet.crop(128,48,128,48);

        newBtn = new BufferedImage[2];
        newBtn[0] = btnSheet.crop(128,96,128,48);
        newBtn[1] = btnSheet.crop(128,144,128,48);

        onBtn = new BufferedImage[2];
        onBtn[0] = btnSheet.crop(128,192,128,48);
        onBtn[1] = btnSheet.crop(128,240,128,48);

        offBtn = new BufferedImage[2];
        offBtn[0] = btnSheet.crop(256,0,128,48);
        offBtn[1] = btnSheet.crop(256,48,128,48);

        optionsBtn= new BufferedImage[2];
        optionsBtn[0] = btnSheet.crop(256,96,128,48);
        optionsBtn[1] = btnSheet.crop(256,144,128,48);

        //TILES
        grass = worldSheet.crop(269,112,width,height);
        tree = worldSheet.crop(209,112,16*3,16*3);
        brad = worldSheet.crop(197,208,16*3,16*3);
        stone = worldSheet.crop(315,190,13*3,13*3);


        //PLAYER
        player_still = playerSheet.crop(3*width-5,0,width,height);

        player_down = new BufferedImage[3];
        player_down[0] = playerSheet.crop(0,0,width-5,height);
        player_down[1] = playerSheet.crop(width,0,width-5,height);
        player_down[2] = playerSheet.crop(2*width,0,width-5,height);

        player_up = new BufferedImage[3];
        player_up[0] = playerSheet.crop(4*width,0,width-5,height);
        player_up[1] = playerSheet.crop(5*width-4,0,width-5,height);
        player_up[2] = playerSheet.crop(6*width,0,width-5,height);

        player_right = new BufferedImage[3];
        player_right[0] = playerSheet.crop(8*width,0,width,height);
        player_right[1] = playerSheet.crop(9*width,0,width,height);
        player_right[2] = playerSheet.crop(10*width-9,0,width,height);

        player_left = new BufferedImage[3];
        player_left[0] = playerSheet.crop(11*width-10,0,width,height);
        player_left[1] = playerSheet.crop(12*width-10,0,width,height);
        player_left[2] = playerSheet.crop(13*width-10,0,width,height);
    }

}
