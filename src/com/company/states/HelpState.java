package com.company.states;

import com.company.Handler;
import com.company.UI.ClickListener;
import com.company.UI.UIImage;
import com.company.UI.UIManager;
import com.company.UI.UIObject;
import com.company.gfx.Assets;

import javax.swing.event.CaretListener;
import java.awt.*;

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
    }

    public void verifEscapeKey(){
        if(handler.getKeyManager().esc){
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setUiManager(handler.getGame().menuState.getUiManager());
        }
    }

    private void drawTitlu(Graphics g,int x,int y){
        Font font1 = new Font("Comic Sans MS", Font.BOLD, 40);

        g.setColor(Color.RED);
        g.setFont(font1);
        g.drawString("Run Bob, run", x, y);

    }

    private void drawScop(Graphics g,int x,int y){
        Font fontSubtitle = new Font("Comic Sans MS", Font.BOLD, 25);
        Font fontDetails = new Font("Comic Sans MS", Font.BOLD, 15);
        int width = handler.getWidth()/2;
        int height = handler.getHeight()/2;

        g.setColor(Color.YELLOW);
        g.setFont(fontSubtitle);
        g.drawString("Scop",x+width/2,y);

        g.setColor(Color.black);
        g.setFont(fontDetails);
        g.drawString("Ajungerea la final inainte de a",(int)(x+width/2.7),(int)(y+height/3));
        g.drawString("se scurge tot timpul din timer",(int)(x+width/2.7),(int)(y+height/3)+20);
    }

    private void drawControls(Graphics g,int x,int y){
        Font fontSubtitle = new Font("Comic Sans MS", Font.BOLD, 25);
        Font fontDetails = new Font("Comic Sans MS", Font.BOLD, 15);
        int width = handler.getWidth()/2;
        int height = handler.getHeight()/2;

        g.setColor(Color.YELLOW);
        g.setFont(fontSubtitle);
        g.drawString("Controls",x+(int)(width/2.5),y);

        g.setColor(Color.black);
        g.setFont(fontDetails);
        g.drawString("KeyUp = move up",(int)(x+width/2.7),(int)(y+height/3)-40);
        g.drawString("KeuDOwn = move down",(int)(x+width/2.7),(int)(y+height/3)-20);
        g.drawString("KeuLeft = move left",(int)(x+width/2.7),(int)(y+height/3));
        g.drawString("KeuRight = move right",(int)(x+width/2.7),(int)(y+height/3)+20);
        g.drawString("Space = action",(int)(x+width/2.7),(int)(y+height/3)+40);
    }

    private void drawTerrain(Graphics g,int x,int y){

    }

    private void drawScor(Graphics g,int x,int y){

    }

    public void drawBackground(Graphics g){
        g.setColor(Color.DARK_GRAY);
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
