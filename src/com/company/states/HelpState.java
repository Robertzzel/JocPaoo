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

    public HelpState(Handler handler, UIManager uiManager){
        super(handler);
        this.uiManager = uiManager;
        handler.getMouseManager().setUiManager(uiManager);

        UIImage image = new UIImage(0,0,100,100, Assets.startBtn[0]);
        uiManager.addObject(image);
    }

    @Override
    public void tick() {
        verifEscapeKey();
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

    public void verifEscapeKey(){
        if(handler.getKeyManager().esc){
            State.setState(handler.getGame().menuState);
            handler.getMouseManager().setUiManager(handler.getGame().uiManagerMenu);
        }
    }
}
