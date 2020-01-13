package com.rocketmail.vaishnavanil.crisis.scene.menu;

import com.rocketmail.vaishnavanil.crisis.Crisis;
import com.rocketmail.vaishnavanil.crisis.display.Screen;
import com.rocketmail.vaishnavanil.crisis.graphics.Button;
import com.rocketmail.vaishnavanil.crisis.graphics.MainMenu;
import com.rocketmail.vaishnavanil.crisis.scene.GameScene;
import com.rocketmail.vaishnavanil.crisis.scene.Scenes;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class MenuScene extends GameScene {
    MainMenu menu;
    Button play;
    Button instructions;
    Button shop;
    Button settings;
    Button credits;
    int xShift = 0;

    public MenuScene(Canvas canvas){
        menu = new MainMenu("/menu.png");
        play = new Button("/playButton.png","/playButtonHover.png",120,25,180,30, "levelselect");
        instructions = new Button("/instructionsbutton.png","/instructionsbuttonHover.png",100,20,200,65,"levelselect");
        shop = new Button("/shopButton.png","/shopButtonHover.png",100,20,200,90,"levelselect");
        settings = new Button("/settings.png","/settingsh.png",100,20,200,115,"levelselect");
        credits = new Button("/creditsButton.png","/creditsButtonHover.png",100,20,200,140,"levelselect");
    }

    @Override
    public boolean renderScene(Screen screen) {
        menu.render(screen);
        instructions.render(screen);
        shop.render(screen);
        settings.render(screen);
        credits.render(screen);
        int dx = Crisis.getInstance().getMouseX();
        int dy = Crisis.getInstance().getMouseY();
        if(!(instructions.mousehover || shop.mousehover || settings.mousehover || credits.mousehover || play.mousehover)) {
            for (int x = 0; x < screen.width; x++) {
                for (int y = 0; y < screen.height; y++) {
                    if (xShift >= 10 * Math.PI) xShift = 0;
                    if (Math.sqrt(Math.pow(Math.abs(y - dy), 2) + Math.pow(Math.abs(x - dx), 2)) < 25) {
                        if (ThreadLocalRandom.current().nextInt(100) + 1 > Math.sqrt(Math.pow(Math.abs(y - dy), 2) + Math.pow(Math.abs(x - dx), 2))) {
                            continue;
                        }
                    }
                    if (Math.tan(Math.sqrt(Math.pow(Math.abs(y - dy), 2) + Math.pow(Math.abs(x - dx), 2))) <= 0)
                        continue;
                    int rnd = ThreadLocalRandom.current().nextInt(100) + 1;
                    if (rnd < 10) {
                        screen.pixels[x + y * screen.width] = ThreadLocalRandom.current().nextInt(0xFFFFFF);
                    }
                }
            }
        }else {
            for (int x = 0; x < screen.width; x++) {
                for (int y = 0; y < screen.height; y++) {
                    if (xShift >= 10 * Math.PI) xShift = 0;
                    int rnd = ThreadLocalRandom.current().nextInt(100) + 1;
                    if (rnd < 2) {
                        screen.pixels[x + y * screen.width] = ThreadLocalRandom.current().nextInt(0xFFFFFF);
                    }
                }
            }
        }
        play.render(screen);
        return false;
    }

    @Override
    public GameScene reload() {
            play.reset();
            instructions.reset();
            shop.reset();
            settings.reset();
            credits.reset();
        return this;
    }
}
