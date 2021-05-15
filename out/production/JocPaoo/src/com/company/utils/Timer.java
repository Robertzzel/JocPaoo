package com.company.utils;

import com.company.Handler;
import com.company.gfx.Assets;
import com.company.states.State;
import java.awt.*;

public class Timer {

    private int min,sec;
    private int backupMin,backupSec;
    private Handler handler;
    private long currentTime;

    public Timer(Handler handler,int min, int sec){
        this.min = min;
        this.sec = sec;
        this.backupMin = min;
        this.backupSec = sec;
        this.currentTime = System.currentTimeMillis();
        this.handler = handler;
    }

    public void tick(){
        if(System.currentTimeMillis() - currentTime > 1000){
            if(sec+min>0){
                if(sec == 0 && min > 0){
                    min--;
                    sec=59;
                }else{
                    sec--;
                }

                actionOnSecond(min+":"+sec);

            }else{
                finalClock();
            }
            currentTime = System.currentTimeMillis();
        }
    }

    private void finalClock(){
        resetClock();
        State.setState(handler.getGame().loseState);
        handler.getMouseManager().setUiManager(handler.getGame().loseState.getUiManager());
        handler.getGame().getDispaly().mesaj.setBackground(Assets.pewter);
    }

    private void resetClock(){
        this.min = this.backupMin;
        this.sec = this.backupSec;
        this.currentTime = System.currentTimeMillis();
    }

    public int getRemainingSeconds(){
        return min*60+sec;
    }

    private void resetClock(int min, int sec){
        this.min = min;
        this.sec = sec;
        this.currentTime = System.currentTimeMillis();
    }

    private void actionOnSecond(String timp){
        handler.getGame().getDispaly().mesaj.setText(timp);
    }

}
