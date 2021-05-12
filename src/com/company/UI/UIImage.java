package com.company.UI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImage extends UIObject{

    private BufferedImage img;

    public UIImage(float x, float y, int width, int height, BufferedImage img) {
        super(x, y, width, height);
        this.img = img;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img,(int)x,(int)y,width,height,null);

    }

    @Override
    public void onClick() {
        //nu trebuie sa faca nimic
    }
}
