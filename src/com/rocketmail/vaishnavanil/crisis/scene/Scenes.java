package com.rocketmail.vaishnavanil.crisis.scene;

import com.rocketmail.vaishnavanil.crisis.Crisis;
import com.rocketmail.vaishnavanil.crisis.scene.game.level1;
import com.rocketmail.vaishnavanil.crisis.scene.menu.MenuScene;
import com.rocketmail.vaishnavanil.crisis.scene.submenu.LevelSelection;

public enum Scenes {
    level1(new level1()),
    levelselect(new LevelSelection(Crisis.getInstance())),
    menu(new MenuScene(Crisis.getInstance())),;

    GameScene curScene;
    Scenes(GameScene scene){
        curScene = scene;
    }

    public GameScene getScene() {

        return curScene;
    }
}
