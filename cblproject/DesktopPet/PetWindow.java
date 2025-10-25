package cblproject.DesktopPet;

import cblproject.Scenes.Scene;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.Timer;

/**
 * Class that is in control of the pets JWindow.
 * @author Marte
 */
public class PetWindow {
    private static JWindow pet = new JWindow();
    private static Timer animationTimer;
    private static JLabel petImage;
    private static ImageIcon petIcon;
    private static int currentFrame = 0;
    private static PetMovement movement;
    private static boolean petSit = false;
    private static ImageIcon[] frames;
    private static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Method that initializes the Pet JWindow.
     */
    public static void petWindow() {
        pet.setAlwaysOnTop(true);
        pet.setBackground(new Color(0, 0, 0, 0));
        String imageLocation = "resources/Bird/Stand/birdstanding.png";
        petIcon = new ImageIcon(imageLocation);
        petImage = new JLabel(petIcon);
        pet.add(petImage);
        pet.pack();
        pet.setLocation(SCREENSIZE.width, 600);
        pet.setVisible(true);

        pet.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Scene.getFrame().setVisible(!Scene.getFrame().isVisible());
            }
        });

        animatePet();
    }

    private static void animatePet() {
        setAnimation(PetFrames.petWalkLeft());
        movement = new PetMovement();
        movement.chooseNewTarget();
        
        if (frames != null && frames.length > 0) {
            animationTimer = new Timer(50, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selectMovement();
                    if (currentFrame >= frames.length) {
                        currentFrame = 0;
                    }
                    
                    petImage.setIcon(frames[currentFrame]);
                    currentFrame = (currentFrame + 1) % frames.length;
                }
            });
            animationTimer.start();
        }
    }

    public static void setAnimation(ImageIcon[] newFrames) {
        frames = newFrames;
    }

    /**
     * Method that selects what type of movement the pet should do.
     */
    public static void selectMovement() {
        if (!petSit) {
            movement.moveToTarget();
        } else {
            movement.moveToSit();
        }
    }

    public static void petSetLocation(int x, int y) {
        pet.setLocation(x, y);
    }

    public static Point petGetLocation() {
        return pet.getLocation();
    }

    public static void setPetSit(boolean petSitToggle) {
        petSit = petSitToggle;
    }

    public static JWindow getPet() {
        return pet;
    }

    public static void togglePetVisibility(boolean visible) {
        pet.setVisible(visible);
    }

    /**
     * Method that disposes of the timer and pet when closing the application.
     */
    public static void dispose() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
        pet.dispose();
    }
}
