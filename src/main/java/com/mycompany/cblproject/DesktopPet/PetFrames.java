package com.mycompany.cblproject.DesktopPet;

import javax.swing.ImageIcon;

/**
 * Class that contains all the animation frames.
 * @author Marte
 */
public class PetFrames {
    private static ImageIcon[] frames;


    /**
     * Method that returns the frames for the pets left walkcycle.
     * @return array of ImageIcons containing frames.
     */
    public static ImageIcon[] petWalkLeft() {
        String animationString = "/Bird/WalkLeft/birdwalkcycle";
        loadFrames(27, animationString);
        return frames;
    }

    /**
     * Method that returns the frames for the pets right walkcycle.
     * @return array of ImageIcons containing frames.
     */
    public static ImageIcon[] petWalkRight() {
        String animationString = "/Bird/WalkRight/birdwalkcycle";
        loadFrames(27, animationString);
        return frames;
    }

    /**
     * Method that returns the frame for the pet standing.
     * @return array of ImageIcons containing frames.
     */
    public static ImageIcon[] petStand() {
        frames = new ImageIcon[1];
        frames[0] = new ImageIcon(PetFrames.class.getResource("/Bird/Stand/birdstanding.png"));
        return frames;
    }

    /**
     * Method that returns the frames for the pet sitting.
     * @return array of ImageIcons containing frames.
     */
    public static ImageIcon[] petSit() {
        String animationString = "/Bird/Sit/birdsitting";
        loadFrames(8, animationString);
        return frames;
    }

    private static void loadFrames(int framesAmount, String animationString) {
        frames = new ImageIcon[framesAmount];
        int currentFrame;
        for (int i = 0; i < framesAmount; i++) {
            currentFrame = i + 1;
            frames[i] = new ImageIcon(
                PetFrames.class.getResource(animationString + currentFrame + ".png"));
        }
    }
}
