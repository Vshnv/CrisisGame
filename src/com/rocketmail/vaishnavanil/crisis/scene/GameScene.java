package com.rocketmail.vaishnavanil.crisis.scene;

import com.rocketmail.vaishnavanil.crisis.display.Screen;

public abstract class GameScene {
    public abstract boolean renderScene(Screen screen);
    public abstract GameScene reload();
}
