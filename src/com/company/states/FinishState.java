package com.company.states;

import com.company.Handler;
import com.company.UI.UIManager;
import com.company.gfx.Assets;

import java.awt.*;

public class FinishState extends State{

    private UIManager uiManager;

    public FinishState(Handler handler) {
        super(handler);
        this.uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
    }

    public void verifEscapeKey(){
        if(handler.getKeyManager().esc){
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setUiManager(handler.getGame().menuState.getUiManager());
            GameState.lvl = 1;
            GameState.secRamase=0;
            GameState.killedMobs=0;
        }
    }

    @Override
    public void tick() {
        uiManager.tick();
        verifEscapeKey();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
        drawBackground(g);
        g.setColor(Color.black);
        g.setFont(Assets.fontSubtitle);
        drawCongrats(g,100,100);
        drawDog(g,200,200);
        drawScor(g,300,300);

    }

    public void drawBackground(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
    }

    public void drawCongrats(Graphics g,int x, int y){
        String mesaj = "Cougratulations!";
        g.drawString(mesaj,x, y);
    }

    public void drawDog(Graphics g,int x,int y){
        g.drawImage(Assets.winDog,x,y,200,200,null);
    }

    public void drawScor(Graphics g,int x, int y){
        g.drawString(GameState.secRamase + "* 6 + "+ GameState.killedMobs,x,y);
    }

    @Override
    public UIManager getUiManager() {
        return uiManager;
    }

    @Override
    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }
}
