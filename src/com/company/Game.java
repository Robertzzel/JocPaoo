package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{

    private Display dispaly;
    public int width , height;
    public String title;

    private boolean running  = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;
    

    public Game(String title,int width,int heigh){
        this.title = title;
        this.width = width;
        this.height = heigh;
    }

    public void init(){
        dispaly = new Display(title,width,height);
    }

    private void tick(){

    }

    private void render(){
        bs = dispaly.getCanvas().getBufferStrategy();
        if(bs == null){
            dispaly.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0,0,width,height);
        //Desenare


        //final desen
        bs.show();
        g.dispose();
    }

    public void run(){
        init();

        while (running){
            tick();
            render();
        }

        stop();
    }

    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        }catch (InterruptedException e){

        }

    }

}
