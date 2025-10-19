package com.mycompany.cblproject.Scenes;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.mycompany.cblproject.DesktopPet.PetFrames;
import com.mycompany.cblproject.DesktopPet.PetMovement;
import com.mycompany.cblproject.DesktopPet.PetWindow;



public class GameMenu {


    private static JPanel gamePanel;
    private static JButton button1;
    private static JButton button3;
    private static JButton button2;
    private static JButton button4;
    private static boolean petSit;

    public static JPanel gameMenu() {
        gamePanel = new JPanel();

        JLabel info = new JLabel("Game Menu", SwingConstants.CENTER);
        gamePanel.add(info, BorderLayout.CENTER);

        button1 = new JButton("BYE!");
        button2 = new JButton("TicTacToe");
        button3 = new JButton("Memory");
        button4 = new JButton("Corner");

        TicTacToeScene.ticTacToePanel();
        MemoryScene.memoryPanel();

        button1.addActionListener(e -> {
            Scene.closeApp();
            
        });

        button2.addActionListener(e -> {
            
            Scene.startNewScene(TicTacToeScene.getTicTacToePanel());
            
        });

        button3.addActionListener(e -> {
            
            Scene.startNewScene(MemoryScene.getMemoryPanel());
            
        });

        button4.addActionListener(e -> {
            if (petSit) {
                petSit = false;
            } else {
                petSit = true;
            }
            PetWindow.setPetSit(petSit);
        });

        gamePanel.add(button1);
        gamePanel.add(button2);
        gamePanel.add(button3);
        gamePanel.add(button4);
        return gamePanel;
    }

    public static JPanel getMenu() {
        return gamePanel;
    }
}
