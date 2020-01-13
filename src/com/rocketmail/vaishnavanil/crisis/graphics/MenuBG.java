package com.rocketmail.vaishnavanil.crisis.graphics;

import com.rocketmail.vaishnavanil.crisis.display.Screen;
import com.rocketmail.vaishnavanil.crisis.sprite.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

public class MenuBG {
    int[] pixels;
    public MenuBG(String path){
        load(path);
    }
    public int[] getPixels(){
        return  pixels.clone();
    }
    public void load(String path){
        try {
            BufferedImage bg = ImageIO.read(SpriteSheet.class.getResource(path));
            pixels = new int[bg.getHeight() * bg.getWidth()];
            bg.getRGB(0,0,bg.getWidth(),bg.getHeight(),pixels,0,bg.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void render(Screen screen){
        for(int i = 0; i<screen.pixels.length;i++){
            screen.pixels[i] = pixels[i];
        }
    }
}
