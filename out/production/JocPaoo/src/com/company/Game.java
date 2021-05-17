package com.company;

import com.company.gfx.Assets;
import com.company.gfx.GameCamera;
import com.company.input.KeyManager;
import com.company.input.MouseManager;
import com.company.states.*;
import com.company.utils.DatabaseManager;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    public static int lvl = 1;
    public static int secRamase = 0;
    public static int killedMobs = 0;

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
    public State finishState;
    public State loseState;

    //Inout
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    //DB
    DatabaseManager dbManager = null;

    public Game(String title,int width,int heigh){
        this.title = title;
        this.width = width;
        this.height = heigh;
        this.keyManager = new KeyManager();
        this.mouseManager = new MouseManager();
        this.dbManager = new DatabaseManager();
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
        finishState = new FinishState(handler);
        loseState = new LoseState(handler);

       swichState("menuState");
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

    public static void resetScore(){
        lvl = 1;
        secRamase = 0;
        killedMobs = 0;
    }

    public void swichState(String state) {
        if (state == "gameState") {
            handler.getGame().getDispaly().mesaj.setBackground(new Color(25, 117, 84));
            handler.getMouseManager().setUiManager(null);
            State.setState(handler.getGame().gameState);
        } else if (state == "menuState") {
            handler.getMouseManager().setUiManager(handler.getGame().menuState.getUiManager());
            State.setState(handler.getGame().menuState);
            handler.getGame().getDispaly().mesaj.setBackground(Assets.pewter);
        } else if (state == "helpState") {
            handler.getMouseManager().setUiManager(handler.getGame().helpState.getUiManager());
            State.setState(handler.getGame().helpState);
        } else if (state == "finishState") {
            handler.getMouseManager().setUiManager(handler.getGame().finishState.getUiManager());
            State.setState(handler.getGame().finishState);
            handler.getGame().getDispaly().mesaj.setBackground(Assets.pewter);
        } else if (state == "loseState") {
            System.out.println("Lose");
        } else {
        }
    }

    public Display getDispaly() {
        return dispaly;
    }

    public Graphics getG() {
        return g;
    }

    public DatabaseManager getDbManager() {
        return dbManager;
    }
}
