package com.mycompany.cblproject.DesktopPet;


import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.Timer;

import com.mycompany.cblproject.Scenes.Scene;

public class PetWindow {
    private static JWindow pet = new JWindow();
    private static PetFrames petFrames = new PetFrames();
    private static Timer animationTimer;
    private static JLabel petImage;
    private static ImageIcon petIcon;
    private static int currentFrame = 0;

    public static void PetWindow() {
        pet.setAlwaysOnTop(true);
        pet.setBackground(new Color(0, 0, 0, 0));
        
        petIcon = new ImageIcon("src/main/java/com/mycompany/resources/Bird/Stand/birdstanding.png");

        petImage = new JLabel(petIcon);
        
        pet.add(petImage);
        pet.pack();

        pet.setLocation(800, 600);
        pet.setVisible(true);

        pet.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Scene.getFrame().setVisible(!Scene.getFrame().isVisible());
            }
        });

        animatePet();
    }

    private static void animatePet() {
        ImageIcon[] frames = petFrames.petWalk();
        PetMovement movement = new PetMovement();
        movement.chooseNewTarget();
        
        if (frames != null && frames.length > 0) {
            animationTimer = new Timer(50, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    movement.moveToTarget();
                    petImage.setIcon(frames[currentFrame]);
                    currentFrame = (currentFrame + 1) % frames.length;
                }
            });
            animationTimer.start();
        }
    }

    public static void petSetLocation(int x, int y) {
        pet.setLocation(x, y);
    }

    public static Point petGetLocation() {
        return pet.getLocation();
    }

    public static JWindow getPet() {
        return pet;
    }

    public static void togglePetVisibility(boolean visible) {
        pet.setVisible(visible);
    }

    public static void dispose() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
        pet.dispose();
    }
}
