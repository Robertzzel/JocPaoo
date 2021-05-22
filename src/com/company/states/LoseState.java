package com.company.states;

import com.company.Game;
import com.company.Handler;
import com.company.UI.UIManager;
import com.company.gfx.Assets;

import java.awt.*;

public class LoseState extends State{

    private UIManager uiManager;

    public LoseState(Handler handler) {
        super(handler);
        this.uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
    }

    public void verifEscapeKey(){
        if(handler.getKeyManager().esc){
            Game.resetScore();
            handler.getGame().getDbManager().insertData("player");
            handler.getGame().swichState("menuState");
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
        drawMesage(g,350,250);
        drawDog(g,525,350);
    }

    public void drawBackground(Graphics g){
        g.setColor(Assets.pewter);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
    }

    public void drawMesage(Graphics g,int x, int y){
        g.setColor(Assets.olive);
        g.setFont(Assets.fontTitle);
        String mesaj = "Nu e nimic, mai incearca!";
        g.drawString(mesaj,x, y);
    }

    public void drawDog(Graphics g,int x,int y){
        g.drawImage(Assets.loseDog,x,y,200,200,null);
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
