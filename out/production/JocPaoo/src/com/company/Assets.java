package com.company;

import java.awt.font.ImageGraphicAttribute;
import java.awt.image.BufferedImage;

public class Assets {

    public static final int width = 8 , height = 8;
    public static BufferedImage player, dirt, grass, stone, tree;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprite.png"));

        dirt = sheet.crop(0,0,width,height);
        grass = sheet.crop(width,0,width,height);
        tree = sheet.crop(width+width,0,width,height);
        stone = sheet.crop(width*3,0,width,height);
        player = sheet.crop(width*4,0,width,height);
    }

}
