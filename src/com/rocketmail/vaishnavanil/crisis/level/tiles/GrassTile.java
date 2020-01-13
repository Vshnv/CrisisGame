package com.rocketmail.vaishnavanil.crisis.level.tiles;


import com.rocketmail.vaishnavanil.crisis.display.Screen;
import com.rocketmail.vaishnavanil.crisis.sprite.Sprite;

public class GrassTile extends Tile{
    public GrassTile(Sprite sprite) {
        super(sprite);
    }
    @Override
    public void render(int x, int y, Screen screen){
        screen.renderTile(x<<4,y<<4,this);
    }

    public boolean solid(){
        return true;
    }

}
