package com.mycompany.cblproject.Scenes;

import com.mycompany.cblproject.DesktopPet.PetWindow;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates the main menu.
 */
public class MainMenu {
    public static JPanel menu;

    /**
     * Method that creates the main menu.
     */
    public static void mainMenu() {
        menu = new JPanel();

        ImageIcon backgroundImage = new ImageIcon(
            MainMenu.class.getResource("/Menus/mainMenuBG.png"));
        JLabel background = new JLabel(backgroundImage);
        background.setLayout(new GridBagLayout()); 

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton buttonStart = new JButton();
        buttonStart.setIcon(new ImageIcon(
            MainMenu.class.getResource("/Menus/startButton.png")));
        buttonStart.setBorderPainted(false);;
        buttonStart.setContentAreaFilled(false);
        buttonStart.setFocusPainted(false);
        buttonStart.setOpaque(false);

        buttonStart.addActionListener(e -> {      
            GameMenu.gameMenu();
            Scene.startNewScene(GameMenu.getMenu(), "Games Menu");
            Scene.getFrame().setVisible(false);
            Scene.getFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            PetWindow.petWindow();
        });

        JButton buttonQuit = new JButton();

        buttonQuit.setIcon(new ImageIcon(
            MainMenu.class.getResource("/Menus/exitButton.png")));
        buttonQuit.setBorderPainted(false);;
        buttonQuit.setContentAreaFilled(false);
        buttonQuit.setFocusPainted(false);
        buttonQuit.setOpaque(false);

        buttonQuit.addActionListener(e -> {      
            Scene.closeApp();
        });

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonQuit);
        background.add(buttonPanel);

        menu.add(background, BorderLayout.CENTER);
    }

    public static JPanel getMenu() {
        return menu;
    }
}
