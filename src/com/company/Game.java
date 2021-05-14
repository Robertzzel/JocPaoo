package com.company;

import com.company.UI.UIManager;
import com.company.gfx.Assets;
import com.company.gfx.GameCamera;
import com.company.input.KeyManager;
import com.company.input.MouseManager;
import com.company.states.GameState;
import com.company.states.HelpState;
import com.company.states.MenuState;
import com.company.states.State;

import java.awt.*;
import java.awt.event.WindowEvent;
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
    public State gameState;
    public State menuState;
    public State helpState;

    //Inout
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    public Game(String title,int width,int heigh){
        this.title = title;
        this.width = width;
        this.height = heigh;
        this.keyManager = new KeyManager();
        this.mouseManager = new MouseManager();
    }

    public void init(){
        dispaly = new Display(title,width,height);
        dispaly.getFrame().addKeyListener(keyManager);
        dispaly.getCanvas().addMouseListener(mouseManager);
        dispaly.getCanvas().addMouseMotionListener(mouseManager);

        dispaly.getFrame().addMouseListener(mouseManager);
        dispaly.getFrame().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0,0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        helpState = new HelpState(handler);

        State.setState(menuState);
        handler.getMouseManager().setUiManager(menuState.getUiManager());
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

    public MouseManager getMouseManager() {
        return mouseManager;
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

    public Display getDispaly() {
        return dispaly;
    }

    public Graphics getG() {
        return g;
    }
}
