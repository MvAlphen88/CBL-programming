package com.mycompany.cblproject.DesktopPet;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

public class PetMovement {
    private int targetX;
    private int targetY;
    private final Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int moveSpeed = 7;
    private Random random = new Random();
    private int maxX;
    private int maxY;
    private boolean currentlySit;

    public void chooseNewTarget() {
        maxX = screensize.width - PetWindow.getPet().getWidth();
        maxY = screensize.height - PetWindow.getPet().getHeight();

        targetX = random.nextInt(maxX);
        targetY = random.nextInt(maxY);
        
        mirrorAnimation();

    }

    public void moveToSit() {
        if(currentlySit) {
            return;
        }

        targetX = screensize.width - PetWindow.getPet().getWidth();
        targetY = screensize.height - PetWindow.getPet().getHeight();
        int x = PetWindow.petGetLocation().x;
        int y = PetWindow.petGetLocation().y;

        mirrorAnimation();

        if (x == targetX && y == targetY) {
            PetWindow.setAnimation(PetFrames.petSit());
        } else {
            moveStep();
        }

    }

    private void mirrorAnimation() {
        if (targetX > PetWindow.petGetLocation().x) {
            PetWindow.setAnimation(PetFrames.petWalkRight());
        } else {
            PetWindow.setAnimation(PetFrames.petWalkLeft());
        }
    }

    public void moveToTarget() {
        
        int x = PetWindow.petGetLocation().x;
        int y = PetWindow.petGetLocation().y;

        if (x == targetX && y == targetY) {
            chooseNewTarget();
            return;
        }

        moveStep();
        
    }

    public void moveStep() {
        int x = PetWindow.petGetLocation().x;
        int y = PetWindow.petGetLocation().y;
        int dx = targetX - x;
        int dy = targetY - y;
        

        if (Math.abs(dx) > moveSpeed) {
            dx = moveSpeed * Integer.signum(dx);
        }
        if (Math.abs(dy) > moveSpeed) {
            dy = moveSpeed * Integer.signum(dy);
        }

        

        PetWindow.petSetLocation(x + dx, y + dy);
    }

    public void petSit() {
        targetX = maxX;
        targetY = maxY;
        int x = PetWindow.petGetLocation().x;
        int y = PetWindow.petGetLocation().y;

        if (x == targetX && y == targetY) {
            
            return;
        }
    }
}
