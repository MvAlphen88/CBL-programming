package cblproject.Scenes;

import cblproject.DesktopPet.PetWindow;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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

        JLabel info = new JLabel("Main Menu", SwingConstants.CENTER);
        menu.add(info, BorderLayout.CENTER);

        JButton button1 = new JButton("Continue");
        button1.addActionListener(e -> {      
            GameMenu.gameMenu();
            Scene.startNewScene(GameMenu.getMenu(), "Games Menu");
            Scene.getFrame().setVisible(false);
            PetWindow.petWindow();
        });

        menu.add(button1);
        
    }

    public static JPanel getMenu() {
        return menu;
    }
}

