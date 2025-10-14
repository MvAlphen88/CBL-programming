package com.mycompany.cblproject.Scenes;



import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

import com.mycompany.cblproject.DesktopPet.PetWindow;



/**
 * Class that creates the JFrame used. It can be filled with different JPanels.
 */
public class Scene {

    private static JFrame frame;
    private static JPanel currentScene;
    private static PetWindow petWindow;

    /**
     * Creates a new JFrame.
     */
    public static void createScene() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); // 
        frame.setLayout(new BorderLayout());
    }

    public static void startNewScene(JPanel newScene) {
        stopCurrentScene();
        frame.add(newScene);
        frame.revalidate();
        frame.repaint();
        currentScene = newScene;
    }

    private static void stopCurrentScene() {
        if (currentScene != null) {
            frame.remove(currentScene);
        }
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void addPet(PetWindow pet) {
        petWindow = pet;
    }

    public static void toggleVisibility(boolean visible) {
        frame.setVisible(visible);

    }

    public static void closeApp() {
        if (petWindow != null) {
            petWindow.dispose();
        }
        if (frame != null) {
            frame.dispose();
        }
        System.exit(0);
    }

}

