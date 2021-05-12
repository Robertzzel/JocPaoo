package com.company.UI;

import com.company.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

    private Handler handler;
    private ArrayList<UIObject> objects;

    public UIManager(Handler handler){
        this.handler = handler;
        this.objects = new ArrayList<UIObject>();
    }

    public void tick(){
        for(UIObject o : objects){
            o.tick();
        }
    }

    public void render(Graphics g){
        for(UIObject o : objects){
            o.render(g);
        }
    }

    public void onMouseMove(MouseEvent e){
        for(UIObject o : objects){
            o.onMouseMove(e);
        }
    }

    public void onMouseRelese(MouseEvent e){
        for(UIObject o : objects){
            o.onMouseRelese(e);
        }
    }

    public void addObject(UIObject o){
        objects.add(o);
    }

    public void remObject(UIObject o){
        objects.remove(o);
    }

}
