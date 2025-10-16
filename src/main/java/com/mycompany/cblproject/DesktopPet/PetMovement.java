package com.mycompany.cblproject.DesktopPet;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Random;

public class PetMovement {
    private int targetX;
    private int targetY;
    private final Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int moveSpeed = 7;
    private Random random = new Random();

    public void chooseNewTarget() {
        int maxX = screensize.width - PetWindow.getPet().getWidth();
        int maxY = screensize.height - PetWindow.getPet().getHeight();

        targetX = random.nextInt(maxX);
        targetY = random.nextInt(maxY);
        
    }

    public void moveToTarget() {
        
        int x = PetWindow.petGetLocation().x;
        int y = PetWindow.petGetLocation().y;

        if (x == targetX && y == targetY) {
            chooseNewTarget();
            return;
        }
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
}
