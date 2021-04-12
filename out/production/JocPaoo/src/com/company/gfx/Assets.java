package com.company.gfx;

import com.company.gfx.ImageLoader;
import com.company.gfx.SpriteSheet;

import java.awt.image.BufferedImage;

public class Assets {

    public static final int width = 32 , height = 32;
    public static BufferedImage grass, stone, tree, brad;
    public static BufferedImage player;

    public static void init(){

        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        SpriteSheet worldSheet = new SpriteSheet(ImageLoader.loadImage("/textures/world2.png"));

        grass = worldSheet.crop(269,112,width,height);
        player = playerSheet.crop(6*width,0,width,height);
        tree = worldSheet.crop(209,112,16*3,16*3);
        brad = worldSheet.crop(197,208,16*3,16*3);
        stone = worldSheet.crop(315,190,13*3,13*3);
    }

}
