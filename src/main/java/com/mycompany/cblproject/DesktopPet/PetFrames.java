package com.mycompany.cblproject.DesktopPet;

import javax.swing.ImageIcon;

public class PetFrames {
    private static ImageIcon[] frames;

    public static ImageIcon[] petWalkLeft() {
        String animationString = "src/main/java/com/mycompany/resources/Bird/WalkLeft/birdwalkcycle";
        loadFrames(27, animationString);
        return frames;
    }

    public static ImageIcon[] petWalkRight() {
        String animationString = "src/main/java/com/mycompany/resources/Bird/WalkRight/birdwalkcycle";
        loadFrames(27, animationString);
        return frames;
    }


    public static ImageIcon[] petStand() {
        frames = new ImageIcon[1];
        frames[0] = new ImageIcon("src/main/java/com/mycompany/resources/Bird/Stand/birdstanding.png");
        return frames;
    }

    public static ImageIcon[] petSit() {
        String animationString = "src/main/java/com/mycompany/resources/Bird/Sit/birdsitting";
        loadFrames(4, animationString);
        return frames;
    }

    private static void loadFrames(int framesAmount, String animationString) {
        frames = new ImageIcon[framesAmount];
        int currentFrame;
        for (int i = 0; i < framesAmount; i++) {
            currentFrame = i + 1;
            frames[i] = new ImageIcon(animationString + currentFrame + ".png");
        }
    }
}
