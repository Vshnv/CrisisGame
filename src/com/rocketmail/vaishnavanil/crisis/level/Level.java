package com.rocketmail.vaishnavanil.crisis.level;


import com.rocketmail.vaishnavanil.crisis.display.Screen;
import com.rocketmail.vaishnavanil.crisis.level.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Level {
    protected int width,height;
    protected int[] tiles;
    public Level(int width,int height){
        this.width = width;
        this.height = height;
        tiles = new int[width*height];
        generateLevel();
    }
    public Level(String p){
        loadLevel(p);
    }
    private void loadLevel(String name){
        BufferedImage levelMap = null;
        try {
            levelMap = ImageIO.read(Level.class.getResource("/" + name+".png"));
            this.width = levelMap.getWidth();
            this.height = levelMap.getHeight();
            int[] pixels = new int[width*height];
            levelMap.getRGB(0,0,width,height,pixels,0,width);
            if(pixels == null) throw new NullPointerException("Could not load defined level data from image! Contact developer!");
            for(int i= 0; i<pixels.length;i++){
                switch (pixels[i]){
                    case 0:break;
                    case 1:break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void generateLevel(){
        /*TESTING TODO::Clear method*/
        for(int y = 0; y <height ; y++){
            for(int x = 0; x<width;x++){
                tiles[x+y*width] = random.nextInt(4);
            }
        }
        tiles[0] = 2;
    }
    public void render(int xScroll, int yScroll, Screen screen){
        screen.setOffset(xScroll,yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = (yScroll >> 4 );
        int y1 = (yScroll + screen.height+16) >> 4;

        for(int y = y0; y< y1;y++){
            for(int x = x0; x<x1;x++){
                getTile(x,y).render(x,y,screen);
            }
        }
    }
    public Tile getTile(int x, int y){
        if(x<0|| y<0 || x>= width || y>=height)return  null;
        switch (tiles[x+y*width]){
            case 0:
                return null;
            default:
                return null;
        }
    }
    public void setTile(int x,int y,int tileID){
        if(x<0|| y<0 || x>= width || y>=height)return;
        tiles[x+y*width] = tileID;
    }
    public void update(){}
    public void time(){}


    private static final Random random = new Random();

}

