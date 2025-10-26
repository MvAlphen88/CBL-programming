package com.mycompany.cblproject.Scenes;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that creates the victory scene after beating a game of tic tac toe.
 */
public class TicTacToeVictoryScene {

    private static JPanel victoryScene;
    private static JLabel background;

    /**
     * Method that creates a victory scene panel for the memory game.
     */
    public static void createVictoryScene() {
        victoryScene = new JPanel(new BorderLayout());

        background = new JLabel();
        background.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);

        JButton buttonRetry = new JButton("Retry");

        buttonRetry.addActionListener(e -> {
            Scene.startNewScene(TicTacToeScene.getTicTacToePanel(), "Tic-Tac-Toe");
        });

        JButton buttonMenu = new JButton("Menu");

        buttonMenu.addActionListener(e -> {
            Scene.startNewScene(GameMenu.getMenu(), "Games Menu");
        });

        buttonPanel.add(buttonRetry, BorderLayout.WEST);
        buttonPanel.add(buttonMenu, BorderLayout.EAST);

        background.add(buttonPanel, BorderLayout.SOUTH);

        victoryScene.add(background, BorderLayout.CENTER);
    }

    /**
     * Method that sets the background image to the defeat screen.
     */
    public static void setImageDefeat() {
        background.setIcon(new ImageIcon(
                TicTacToeVictoryScene.class.getResource("/Menus/tttDefeat.png")));
        background.revalidate();
        background.repaint();
    }

    /**
     * Method that sets the background image to the draw screen.
     */
    public static void setImageDraw() {
        background.setIcon(new ImageIcon(
                TicTacToeVictoryScene.class.getResource("/Menus/tttDraw.png")));
        background.revalidate();
        background.repaint();
    }

    /**
     * Method that sets the background image to the victory screen.
     */
    public static void setImageVictory() {
        background.setIcon(new ImageIcon(
                TicTacToeVictoryScene.class.getResource("/Menus/tttVictory.png")));
        background.revalidate();
        background.repaint();
    }

    public static JPanel getVictoryPanel() {
        return victoryScene;
    }
}
