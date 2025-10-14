package com.mycompany.cblproject.Scenes;


import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TicTacToeScene {
    private static JPanel ticTacToePanel;

    public static void ticTacToePanel() {
        ticTacToePanel = new JPanel();
        JLabel info = new JLabel("Tic-Tac-Toe", SwingConstants.CENTER);
        ticTacToePanel.add(info, BorderLayout.CENTER);

        JButton button = new JButton("Return to menu!");


        button.addActionListener(e -> {
            Scene.startNewScene(GameMenu.getMenu());
            
        });

        ticTacToePanel.add(button);
        
    }

    public static JPanel getTicTacToePanel() {
        return ticTacToePanel;
    }
} 

