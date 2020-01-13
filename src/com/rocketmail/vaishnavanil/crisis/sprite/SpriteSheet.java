package com.rocketmail.vaishnavanil.crisis.sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private String path;
    public final int SIZE;
    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("/spritesheet.png",256);

    public SpriteSheet(String p,int size){
        SIZE = size;
        path = p;
        pixels = new int[size*size];
        load();
    }

    private void load(){
        BufferedImage image = null;
        try {
            image = ImageIO.read(SpriteSheet.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.getRGB(0,0,image.getWidth(),image.getHeight(),pixels,0,image.getWidth());
    }
}