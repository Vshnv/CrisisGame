package com.rocketmail.vaishnavanil.crisis.display;

import com.rocketmail.vaishnavanil.crisis.level.tiles.Tile;
import com.rocketmail.vaishnavanil.crisis.sprite.Sprite;

import java.util.Random;

public class Screen {
    public int width,height;
    public  int[] pixels;
    private final int MAP_SIZE = 16;
    private final int MAP_SIZE_MASK = MAP_SIZE-1;
    public int[] tiles = new int[64*64];
    public  int xOffset,yOffset;
    private Random r = new Random();
    public Screen(int w, int h){
        width = w;
        height = h;
        pixels = new int[w*h];
        for(int i = 0; i<64*64;i++){
            tiles[i] = r.nextInt(0xffffff);
        }
    }
    public void clear(){
        for(int i = 0;i<pixels.length;i++){
            pixels[i] = 0;
        }
    }
/*
    public void render(int xOffset,int yOffset){
        for(int y = 0; y<height;y++){
            int yp = y + yOffset;
            if(yp<0||yp>=height)continue;
              for(int x = 0; x<width;x++){
                int xp = x + xOffset;
                if(xp<0||xp>=width)continue;
                pixels[xp+yp*width] = Sprite.grass.pixels[(x & 15) + (y&15)*Sprite.grass.SIZE];
            }
        }
    }*/




    public void renderTile(int xPos, int yPos, Tile tile){
        xPos -= xOffset;
        yPos -= yOffset;
        for(int y = 0; y <tile.sprite.SIZE;y++){
            int ya = y + yPos;
            for(int x = 0; x <tile.sprite.SIZE;x++){
                int xa = x + xPos;
                if(xa <-tile.sprite.SIZE || xa >= width ||ya <0 || ya >= height)break;
                if(xa <0) xa=0;
                pixels[xa+ya*width] = tile.sprite.pixels[x + y*tile.sprite.SIZE];

            }
        }
    }

    public void renderPlayer(int xPos, int yPos, Sprite sprite){
        xPos -= xOffset;
        yPos -= yOffset;
        for(int y = 0; y <16;y++){
            int ya = y + yPos;
            for(int x = 0; x <16;x++){
                int xa = x + xPos;
                if(xa <-16 || xa >= width ||ya <0 || ya >= height)break;
                if(xa <0) xa=0;
                int pix = sprite.pixels[x + y*16];
                if(pix != 0xFFFFFFFF)pixels[xa+(ya)*width] = pix;

            }
        }
    }
    public void renderPixels(int[] colorHash,int xOff,int yOff,int w,int h){
        for(int x = xOff;x<w+xOff;x++){
            for(int y = yOff;y<h+yOff;y++){
                pixels[x+y*this.width] = colorHash[x-xOff+(y-yOff)*w];
            }
        }
    }
    public void setOffset(int xO,int yO){
        xOffset = xO;
        yOffset = yO;
    }


}