package com.rocketmail.vaishnavanil.crisis.level.tiles;


import com.rocketmail.vaishnavanil.crisis.display.Screen;
import com.rocketmail.vaishnavanil.crisis.sprite.Sprite;

public class Tile {
    public int x,y;
    public Sprite sprite;


    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){}

    public boolean solid(){
        return false;
    }
}
