package com.mycompany.cblproject.Scenes;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.mycompany.cblproject.DesktopPet.PetWindow;


/**
 * Creates the main menu.
 */
public class MainMenu {
    public static JPanel menu;
    public static void mainMenu() {
        menu = new JPanel();

        
        JLabel info = new JLabel("Main Menu", SwingConstants.CENTER);
        menu.add(info, BorderLayout.CENTER);

        JButton button1 = new JButton("Continue");
        button1.addActionListener(e -> {
            PetWindow pet = new PetWindow();
            Scene.addPet(pet);
            GameMenu.gameMenu();
            Scene.startNewScene(GameMenu.getMenu());
            Scene.toggleVisibility(false);
        });

        menu.add(button1);
        
    }

    public static JPanel getMenu() {
        return menu;
    }
}
