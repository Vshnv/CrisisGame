package com.rocketmail.vaishnavanil.crisis.scene.submenu;

import com.rocketmail.vaishnavanil.crisis.Crisis;
import com.rocketmail.vaishnavanil.crisis.display.Screen;
import com.rocketmail.vaishnavanil.crisis.graphics.Button;
import com.rocketmail.vaishnavanil.crisis.graphics.MenuBG;
import com.rocketmail.vaishnavanil.crisis.level.Level;
import com.rocketmail.vaishnavanil.crisis.scene.GameScene;
import com.rocketmail.vaishnavanil.crisis.scene.Scenes;
import com.rocketmail.vaishnavanil.crisis.scene.game.Levels;
import com.rocketmail.vaishnavanil.crisis.scene.menu.MenuScene;

import java.awt.*;

public class LevelSelection extends GameScene {
    MenuBG bg;
    com.rocketmail.vaishnavanil.crisis.graphics.Button[] lbtn = new Button[Levels.values().length];
    static Button locked = new Button(Levels.lockedicon,30,30,0,0,"menu");
    public LevelSelection(Canvas canvas){
        bg = new MenuBG("/lvlsel.png");
        int index = 0;
        int x = 5;
        int y = 5;
        for(Levels l :Levels.values()){
            int[] icon = l.isUnlocked() ? l.getIcon():Levels.lockedicon;
            if(x+30 > canvas.getWidth()/3){
                x = 5;
                y+=35;
            }

                lbtn[index++] = new Button(l.getIcon(), 30, 30, x, y, "level1");


            x+= 35;
        }

    }
    @Override
    public boolean renderScene(Screen screen) {
        bg.render(screen);
        int x = 5;
        int y = 5;
        for(int i = 0;i<Levels.values().length;i++){
            if(Levels.values()[i].isUnlocked()){
                lbtn[i].render(screen);

            }else{
                for(int xx = lbtn[i].getxOff();xx<lbtn[i].getxOff()+30;xx++){
                    for(int yy = lbtn[i].getyOff();yy<(  lbtn[i].getyOff()+30);yy++){
                        screen.pixels[xx+yy*screen.width] = Levels.lockedicon[xx-lbtn[i].getxOff()+(yy-lbtn[i].getyOff())*30];
                    }
                }
            }
        }
        return true;
    }

    @Override
    public GameScene reload() {
        if(lbtn == null)return this;
        for(Button b:lbtn){
            if(b==null)continue;
            b.reset();
        }
        return this;
    }
}
