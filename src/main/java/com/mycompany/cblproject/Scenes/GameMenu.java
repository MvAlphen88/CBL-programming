package com.mycompany.cblproject.Scenes;

import com.mycompany.cblproject.DesktopPet.PetWindow;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class that creates and controls the Game menu.
 * 
 * @author Marte
 */
public class GameMenu {
    private static JPanel gamePanel;
    private static JButton buttonExit;
    private static JButton buttonTTT;
    private static JButton buttonMemory;
    private static JButton buttonSit;
    private static boolean petSit;
    

    /**
     * Method that creates and returns a the menu containing the games.
     * 
     * @return JPanel of the game menu
     */
    public static JPanel gameMenu() {
        gamePanel = new JPanel();

        

        TicTacToeScene.ticTacToePanel();
        MemoryScene.memoryPanel();
        createButtons();

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 300, 300)); 
        buttonsPanel.add(buttonMemory);
        buttonsPanel.add(buttonTTT);
        buttonsPanel.add(buttonSit);
        buttonsPanel.add(buttonExit);

        gamePanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        return gamePanel;
    }

    private static void createButtons() {
        buttonExit = new JButton();
        buttonTTT = new JButton();
        buttonMemory = new JButton();
        buttonSit = new JButton();

        buttonExit.addActionListener(e -> {
            Scene.closeApp();

        });

        buttonTTT.addActionListener(e -> {

            Scene.startNewScene(TicTacToeScene.getTicTacToePanel(), "Tic-Tac-Toe");

        });

        buttonMemory.addActionListener(e -> {

            Scene.startNewScene(MemoryScene.getMemoryPanel(), "Memory");

        });

        buttonSit.addActionListener(e -> {
            if (petSit) {
                petSit = false;
            } else {
                petSit = true;
            }
            PetWindow.setPetSit(petSit);
        });

        changeButtonAppearance();
    }

    private static void changeButtonAppearance() {
        buttonExit.setIcon(
            new ImageIcon("src/main/java/com/mycompany/resources/Menus/QuitSprite.png"));
        buttonMemory.setIcon(
            new ImageIcon("src/main/java/com/mycompany/resources/Menus/MemorySprite.png"));
        buttonTTT.setIcon(
            new ImageIcon("src/main/java/com/mycompany/resources/Menus/TicTacToeSprite.png"));
        buttonSit.setIcon(
            new ImageIcon("src/main/java/com/mycompany/resources/Menus/RestSprite.png"));
        
        JButton [] gameMenuButtons = new JButton[4];
        gameMenuButtons[0] = buttonExit;
        gameMenuButtons[1] = buttonMemory;
        gameMenuButtons[2] = buttonTTT;
        gameMenuButtons[3] = buttonSit;
        for (int i = 0; i < 4; i++) {
            gameMenuButtons[i].setBorderPainted(false);;
            gameMenuButtons[i].setContentAreaFilled(false);
            gameMenuButtons[i].setFocusPainted(false);
            gameMenuButtons[i].setOpaque(false);
        }
    }

    public static JPanel getMenu() {
        return gamePanel;
    }
}
