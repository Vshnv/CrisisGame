package com.rocketmail.vaishnavanil.crisis.sprite;

public class Sprite {
    public int[] pixels;
    private int x,y;
    public final int SIZE;
    private SpriteSheet sheet;


    public static Sprite grass = new Sprite(0,0,SpriteSheet.tiles,16);
    public static Sprite voidsprite = new Sprite(16,48568);


    public static Sprite player_back = new Sprite(1,13,SpriteSheet.tiles,16);
    public static Sprite player_forward = new Sprite(1,12,SpriteSheet.tiles,16);
    public static Sprite player_left = new Sprite(1,14,SpriteSheet.tiles,16);
    public static Sprite player_right = new Sprite(1,15,SpriteSheet.tiles,16);


    public Sprite(int x,int y, SpriteSheet ss,int size){
        this.x = x*size;
        this.y = y*size;
        this.sheet = ss;
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        load();
    }
    public Sprite(int size, int color){
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        setColor(color);
    }

    private void setColor(int color) {
        for(int i = 0; i<SIZE*SIZE;i++){
            pixels[i] = color;
        }
    }

    private void load(){
        for(int y = 0; y<SIZE;y++){
            for(int x = 0;x<SIZE;x++){
                pixels[x+y*SIZE] = sheet.pixels[(x+this.x)+(y+this.y) * sheet.SIZE];
            }
        }
    }
}