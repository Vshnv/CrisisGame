package com.rocketmail.vaishnavanil.crisis.scene.game;

import com.rocketmail.vaishnavanil.crisis.display.Screen;
import com.rocketmail.vaishnavanil.crisis.graphics.MenuBG;
import com.rocketmail.vaishnavanil.crisis.scene.GameScene;

import java.util.concurrent.ThreadLocalRandom;

public class level1 extends GameScene {
     int[] bg1;
     int[] bg2;
     int xOff = 0,yOff = 0;
    public level1(){
        bg1 = new MenuBG("/bgl1.png").getPixels();
        bg2 = new MenuBG("/bgl2.png").getPixels();
    }
    public int flow(int xa,int max){
        if(xa > max){
            int diff = xa-max;
            if(diff > max){
                return flow(diff,max);
            }
        }
        return xa;
    }
    @Override
    public boolean renderScene(Screen screen) {
       /* for(int x = 0; x<screen.width;x++){
            int xa = x + xOff*2;
            if(xOff >= screen.width)xOff = 0;
            for(int y = 0;y<screen.height;y++){
                int ya = y + yOff*2;
                if(yOff >= screen.height)yOff = 0;
                screen.pixels[x+y*screen.width] = bg1[flow(xa,300)+ flow(ya,200) * screen.width];
            }
        }*/
        for(int x = 0; x<screen.width;x++){
            int xa = x + xOff;
            if(xOff >= screen.width)xOff = 0;
            for(int y = 0;y<screen.height;y++){
                int ya = y + yOff;
                if(yOff >= screen.height)yOff = 0;
                int bgF = bg2[flow(xa,300)+ flow(ya,200) * screen.width];
                if(bgF == -65316){
                    screen.pixels[x+y*screen.width] = bg1[flow(x+2*xOff,300)+ flow(y+2*yOff,200) * screen.width];
                    continue;
                }
                screen.pixels[x+y*screen.width] = bgF;
            }
        }
        if(ThreadLocalRandom.current().nextInt(100)+1 <50){
            xOff++;
        }

        return true;
    }

    @Override
    public GameScene reload() {
        bg1 = new MenuBG("/bgl1.png").getPixels();
        bg2 = new MenuBG("/bgl2.png").getPixels();
        xOff = 0;
        yOff = 0;
        return null;
    }
}
