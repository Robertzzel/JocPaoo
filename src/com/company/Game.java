package com.company;

import com.company.gfx.Assets;
import com.company.gfx.GameCamera;
import com.company.states.GameState;
import com.company.states.MenuState;
import com.company.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display dispaly;
    private int width , height;
    public String title;

    private boolean running  = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    private State gameState;
    private State menuState;

    //Inout
    private KeyManager keyManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    public Game(String title,int width,int heigh){
        this.title = title;
        this.width = width;
        this.height = heigh;
        this.keyManager = new KeyManager();
    }

    public void init(){
        dispaly = new Display(title,width,height);
        dispaly.getFrame().addKeyListener(keyManager);
        Assets.init();

        gameCamera = new GameCamera(this,0,0);
        handler = new Handler(this);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(gameState);
    }

    private void tick(){
        keyManager.tick();
        if(State.getState() != null)
            State.getState().tick();
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

        if(State.getState() != null)
            State.getState().render(g);

        //final desen
        bs.show();
        g.dispose();
    }

    public void run(){
        init();

        int fps = 100;
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
    public KeyManager getKeyManager(){return keyManager;}
    public GameCamera getGameCamera(){return gameCamera;}

    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
