package com.rocketmail.vaishnavanil.crisis.scene.game;

import com.rocketmail.vaishnavanil.crisis.graphics.MenuBG;
import com.rocketmail.vaishnavanil.crisis.level.Level;
import com.rocketmail.vaishnavanil.crisis.sprite.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum Levels {
    l1(true,load("/lbtn1.png")),l2(false,load("/lbtn2.png")),l3(false,load("/lbtn3.png")),l4(false,load("/lbtn4.png")),l5(false,load("/lbtn5.png")),l6(false,load("/lbtn6.png")),l7(false,load("/lbtn7.png"));
    public static int[] lockedicon = load("/lbtnlocked.png");
    int[] icon;
    boolean ulk;
    Levels(boolean unlocked,int[] icon){
        this.icon = icon;
        ulk = unlocked;
    }
    public boolean isUnlocked(){
        return ulk;
    }
    public int[] getIcon(){
        return icon;
    }
    private static int[] load(String path){
        int[] ret = new int[0];
        try {
            BufferedImage bg = ImageIO.read(SpriteSheet.class.getResource(path));
            ret = new int[bg.getHeight() * bg.getWidth()];
            bg.getRGB(0,0,bg.getWidth(),bg.getHeight(),ret,0,bg.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
