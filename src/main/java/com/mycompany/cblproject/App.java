package com.mycompany.cblproject;

import com.mycompany.cblproject.Scenes.MainMenu;
import com.mycompany.cblproject.Scenes.Scene;

/**
 * Class that runs the game.
 * @author Marte
 */
public class App {

    public static void main(String[] args) {
        Scene.createScene();
        MainMenu.mainMenu();
        Scene.startNewScene(MainMenu.getMenu());
    }
}
