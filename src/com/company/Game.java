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
        Assets.init();
    }

    int x=0;
    private void tick(){
        x+=1;
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

        g.drawImage(Assets.tree,x,10,null);

        //final desen
        bs.show();
        g.dispose();
    }

    public void run(){
        init();

        int fps = 61;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer =0;
        int ticks =0 ;

        while (running){

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }
            //verifica fps
            if(timer >= 1000000000){
                System.out.print("\nTicks and framesL "+ticks);
                ticks=0;
                timer=0;
            }

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
