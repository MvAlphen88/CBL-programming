package cblproject;

import cblproject.Scenes.MainMenu;
import cblproject.Scenes.Scene;

/**
 * Class that runs the game.
 * @author Marte
 */
public class App {

    public static void main(String[] args) {
        Scene.createScene();
        MainMenu.mainMenu();
        Scene.startNewScene(MainMenu.getMenu(), "BIRB");
    }
}