package cblproject.Scenes;

import cblproject.DesktopPet.PetWindow;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class that creates the JFrame used. It can be filled with different JPanels.
 * @author Marte
 */
public class Scene {

    private static JFrame frame;
    private static JPanel currentScene;

    /**
     * Creates a new JFrame.
     */
    public static void createScene() {
        frame = new JFrame();
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); // 
        frame.setLayout(new BorderLayout());
    }

    /**
     * Method that starts a new scene. It removes the current JPanel and replaces it
     * with a new one.
     * @param newScene The JPanel for the new scene.
     */
    public static void startNewScene(JPanel newScene, String newSceneTitle) {
        stopCurrentScene();
        frame.add(newScene);
        setTitle(newSceneTitle);
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

    public static void setTitle(String title) {
        frame.setTitle(title);
    }

    /**
     * Method that closes the application.
     */
    public static void closeApp() {
        if (PetWindow.getPet() != null) {
            PetWindow.dispose();
        }
        if (frame != null) {
            frame.dispose();
        }
        System.exit(0);
    }

}


