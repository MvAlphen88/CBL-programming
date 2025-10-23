package com.mycompany.cblproject.Scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.mycompany.cblproject.Games.MemoryGame;

/**
 * Class that creates and controls the memory game.
 * @author Camila
 */
public class MemoryScene {
    private static JPanel memoryPanel;

    /** 
     * Method that creates the panel containing the memory game.
     */
    public static void memoryPanel() {
        memoryPanel = new JPanel(new BorderLayout(10, 10));
        String titleString = "This is a memory game. Match all 12 pairs!";
        JLabel title = new JLabel(titleString, SwingConstants.CENTER);
        memoryPanel.add(title, BorderLayout.NORTH);

        MemoryGame memoryGame = new MemoryGame();
        JPanel gamePanel = memoryGame.createMemoryGame();

        memoryPanel.add(gamePanel);

       
        
        JButton backToMenu = new JButton("Return to menu");
        backToMenu.addActionListener(e -> Scene.startNewScene(GameMenu.getMenu()));
        memoryPanel.add(backToMenu, BorderLayout.SOUTH);

        
    }

    public static JPanel getMemoryPanel() {
        return memoryPanel;
    }
}
