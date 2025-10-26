package com.mycompany.cblproject.Scenes;

import com.mycompany.cblproject.Games.MemoryGame;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that creates and controls the memory game.
 * 
 * @author Camila
 */
public class MemoryScene {
    private static JPanel memoryPanel;
    private static MemoryGame memoryGame;
    private static JPanel gamePanel;
    private static JPanel centerPanel;

    /**
     * Method that creates the panel containing the memory game.
     */
    public static void memoryPanel() {
        MemoryVictoryScene.createVictoryScene();
        memoryPanel = new JPanel(new BorderLayout(10, 10));

        memoryPanel.add(createHeader(), BorderLayout.NORTH);

        memoryGame = new MemoryGame();
        gamePanel = memoryGame.createMemoryGame();

        centerPanel = new JPanel(new java.awt.GridBagLayout());
        centerPanel.add(gamePanel);

        memoryPanel.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Method that creates a header with an image and a button to return to the main
     * menu.
     * 
     * @return JPanel containing the image correlating to the memory game and a
     *         stylized button.
     */
    public static JPanel createHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new javax.swing.OverlayLayout(headerPanel));

        ImageIcon headerIcon = new ImageIcon(
                MemoryScene.class.getResource("/Menus/memoryBg.png"));
        JLabel headerImage = new JLabel(headerIcon);
        headerImage.setBounds(0, 0, headerIcon.getIconWidth(), headerIcon.getIconHeight());

        JButton exitButton = new JButton();

        exitButton.setIcon(
                new ImageIcon(MemoryScene.class.getResource("/Menus/returnButton.png")));
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(false);

        exitButton.setBounds(10, 10,
                exitButton.getPreferredSize().width, exitButton.getPreferredSize().height);
        exitButton.addActionListener(e -> Scene.startNewScene(GameMenu.getMenu(), "Games Menu"));

        headerPanel.add(exitButton);
        headerPanel.add(headerImage);

        return headerPanel;
    }

    /**
     * Method that resets and restarts the game.
     */
    public static void resetGame() {
        centerPanel.removeAll();
        memoryGame = new MemoryGame();
        gamePanel = memoryGame.createMemoryGame();
        centerPanel.add(gamePanel);

        memoryPanel.revalidate();
        memoryPanel.repaint();
    }

    public static JPanel getMemoryPanel() {
        return memoryPanel;
    }
}
