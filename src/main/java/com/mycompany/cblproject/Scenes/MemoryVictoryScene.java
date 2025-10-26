package com.mycompany.cblproject.Scenes;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that creates the scene after winning the memory minigame.
 */
public class MemoryVictoryScene {
    private static JPanel victoryScene;

    /**
     * Method that creates a victory scene panel for the memory game.
     */
    public static void createVictoryScene() {
        victoryScene = new JPanel(new BorderLayout());

        ImageIcon backgroundImage = new ImageIcon(
                "src/main/java/com/mycompany/resources/Menus/memoryVictory.png");
        JLabel background = new JLabel(backgroundImage);
        background.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        JButton buttonRetry = new JButton("Retry");
        JButton buttonMenu = new JButton("Menu");

        buttonRetry.addActionListener(e -> {
            MemoryScene.resetGame();
            Scene.startNewScene(MemoryScene.getMemoryPanel(), "Memory");
        });

        buttonMenu.addActionListener(e -> {
            Scene.startNewScene(GameMenu.getMenu(), "Games Menu");
        });

        buttonPanel.add(buttonRetry, BorderLayout.WEST);
        buttonPanel.add(buttonMenu, BorderLayout.EAST);

        background.add(buttonPanel, BorderLayout.SOUTH);

        victoryScene.add(background, BorderLayout.CENTER);
    }

    public static JPanel getVictoryPanel() {
        return victoryScene;
    }
}
