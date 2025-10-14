package com.mycompany.cblproject.DesktopPet;


import java.awt.Color;
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
    private final JWindow pet = new JWindow();
    PetFrames petFrames = new PetFrames();
    private Timer animationTimer;
    private JLabel petImage;
    private ImageIcon petIcon;
    private int currentFrame = 0;

    public PetWindow() {
        

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

    private void animatePet() {
        ImageIcon[] frames = petFrames.petWalk();
        
        if (frames != null && frames.length > 0) {
            animationTimer = new Timer(50, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    petImage.setIcon(frames[currentFrame]);
                    currentFrame = (currentFrame + 1) % frames.length;
                }
            });
            animationTimer.start();
        }
    }

    public void dispose() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
        pet.dispose();
    }
}
