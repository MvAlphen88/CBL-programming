/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cblproject;

import com.mycompany.cblproject.DesktopPet.PetWindow;
import com.mycompany.cblproject.Scenes.MainMenu;
import com.mycompany.cblproject.Scenes.Scene;

/**
 *
 * @author Marte
 */
public class App {

    public static void main(String[] args) {
        Scene.createScene();
        MainMenu.mainMenu();
        Scene.startNewScene(MainMenu.getMenu());
    }
}
