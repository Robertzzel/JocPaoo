package com.company.gfx;

import com.company.gfx.ImageLoader;
import com.company.gfx.SpriteSheet;

import java.awt.image.BufferedImage;

public class Assets {

    public static final int width = 32 , height = 32;
    public static BufferedImage grass, stone, tree, brad, boat, player_still;
    public static BufferedImage[] player_down, player_up, player_left, player_right;

    public static void init(){

        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        SpriteSheet worldSheet = new SpriteSheet(ImageLoader.loadImage("/textures/world.png"));

        grass = worldSheet.crop(269,112,width,height);
        tree = worldSheet.crop(209,112,16*3,16*3);
        brad = worldSheet.crop(197,208,16*3,16*3);
        stone = worldSheet.crop(315,190,13*3,13*3);

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
