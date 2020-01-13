package com.rocketmail.vaishnavanil.crisis.graphics;

import com.rocketmail.vaishnavanil.crisis.display.Screen;
import com.rocketmail.vaishnavanil.crisis.sprite.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenu {
    private int width = 300;
    private int height = (width/16)*9;
    private int[] pixels;
    public MainMenu(String path){
        load(path);
    }
    public void render(Screen screen){
        screen.renderPixels(pixels,0,0,width,height);
    }
    private void load(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(SpriteSheet.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pixels = new int[image.getWidth()*image.getHeight()];
        image.getRGB(0,0,image.getWidth(),image.getHeight(),pixels,0,image.getWidth());
    }
}
