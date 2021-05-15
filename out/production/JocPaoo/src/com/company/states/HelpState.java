package com.company.states;

import com.company.Handler;
import com.company.UI.ClickListener;
import com.company.UI.UIImage;
import com.company.UI.UIManager;
import com.company.UI.UIObject;
import com.company.gfx.Assets;

import javax.swing.event.CaretListener;
import java.awt.*;

import static com.company.gfx.Assets.fontDetails;
import static com.company.gfx.Assets.fontSubtitle;

public class HelpState extends State{

    private UIManager uiManager;

    public HelpState(Handler handler){
        super(handler);
        this.uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
    }

    @Override
    public void tick() {
        verifEscapeKey();
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
        drawBackground(g);

        drawTitlu(g,handler.getWidth()/2-150,100);
        drawScop(g,0,200);
        drawControls(g,handler.getWidth()/2,200);
        drawTerrain(g,0,500);
        drawScor(g, handler.getWidth()/2, 500);
    }

    public void verifEscapeKey(){
        if(handler.getKeyManager().esc){
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setUiManager(handler.getGame().menuState.getUiManager());
        }
    }


    private void drawScor(Graphics g,int x,int y){
        int width = handler.getWidth()/2;
        int height = handler.getHeight()/2;

        g.setColor(Assets.olive);
        g.setFont(fontSubtitle);
        g.drawString("Scor",x+(int)(width/2)-25,y);

        g.setColor(Assets.oliveGreen);
        g.setFont(fontDetails);
        g.drawString("Scor = (5*timRamas(in secunde)+6*tufeDistruse)/11",(int)(x+width/5),(int)(y+height/3));
    }

    private void drawTerrain(Graphics g,int x,int y){

        int width = handler.getWidth()/2;
        int height = handler.getHeight()/2;

        g.setColor(Assets.olive);
        g.setFont(Assets.fontSubtitle);
        g.drawString("Terrain",x+(int)(width/2)-25,y);

        g.setColor(Assets.oliveGreen);
        g.setFont(fontDetails);
        g.drawImage(Assets.tree,x+width/4,y+40,40,40,null);
        g.drawImage(Assets.brad,x+width/4+45,y+40,40,40,null);
        g.drawImage(Assets.stone,x+width/4+90,y+40,40,40,null);
        g.drawString("-> solide, nu pot fi distruse",x+width/4+135,y+60);

        g.drawImage(Assets.bush1,x+width/4,y+90,40,40,null);
        g.drawString("-> solid, poate fi distrus, viata = 1",x+width/4+45,y+110);
        g.drawImage(Assets.bush2,x+width/4,y+135,40,40,null);
        g.drawString("-> solid, poate fi distrus, viata = 2",x+width/4+45,y+155);

    }

    private void drawTitlu(Graphics g,int x,int y){

        g.setColor(Assets.green);
        g.setFont(Assets.fontTitle);
        g.drawString("Run Bob, run", x, y);

    }

    private void drawScop(Graphics g,int x,int y){
        int width = handler.getWidth()/2;
        int height = handler.getHeight()/2;

        g.setColor(Assets.olive);
        g.setFont(fontSubtitle);
        g.drawString("Scop",x+width/2,y);

        g.setColor(Assets.oliveGreen);
        g.setFont(fontDetails);
        g.drawString("Ajungerea la final inainte de a",(int)(x+width/2.7),(int)(y+height/3));
        g.drawString("se scurge tot timpul din timer",(int)(x+width/2.7),(int)(y+height/3)+20);
    }

    private void drawControls(Graphics g,int x,int y){
        int width = handler.getWidth()/2;
        int height = handler.getHeight()/2;

        g.setColor(Assets.olive);
        g.setFont(fontSubtitle);
        g.drawString("Controls",x+(int)(width/2.5),y);

        g.setColor(Assets.oliveGreen);
        g.setFont(fontDetails);
        g.drawString("KeyUp = move up",(int)(x+width/2.7),(int)(y+height/3)-40);
        g.drawString("KeuDOwn = move down",(int)(x+width/2.7),(int)(y+height/3)-20);
        g.drawString("KeuLeft = move left",(int)(x+width/2.7),(int)(y+height/3));
        g.drawString("KeuRight = move right",(int)(x+width/2.7),(int)(y+height/3)+20);
        g.drawString("Space = action",(int)(x+width/2.7),(int)(y+height/3)+40);
    }

    public void drawBackground(Graphics g){
        g.setColor(Assets.pewter);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
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
