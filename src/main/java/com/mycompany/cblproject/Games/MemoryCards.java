package com.mycompany.cblproject.Games;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Class that creates the cards for the memory game.
 * @author Camila & Marte
 */
public class MemoryCards {
    private int value;
    private JButton cardButton;
    private ImageIcon frontImage;
    private ImageIcon backImage;
    private boolean matched;

    /**
     * Method that creates memory cards.
     * 
     * @param value value of the memory card we check to see if 2 cards match
     * @param frontImage the display image of the front of the card
     * @param backImage the display image of the back of the card
     */
    public MemoryCards(int value, ImageIcon frontImage, ImageIcon backImage) {
        this.value = value;
        this.frontImage = frontImage;
        this.backImage = backImage;

        cardButton = new JButton();
        cardButton.setIcon(backImage);
        cardButton.setBorderPainted(false);
        cardButton.setContentAreaFilled(false);
        cardButton.setFocusPainted(false);
        cardButton.setOpaque(false);

        cardButton.setMaximumSize(new Dimension(100, 100));
        cardButton.setBorder(BorderFactory.createEmptyBorder());
    }

    public JButton getButton() {
        return cardButton;
    }

    public int getValue() {
        return value;
    }

    public void showFront() {
        cardButton.setIcon(frontImage);
    }

    /**
     * Method that sets the image to the card back if the card has not yet been matched.
     */
    public void showBack() {
        if (!matched) {
            cardButton.setIcon(backImage);
        }
    }

    /**
     * Method that disables the button when it has been matched so it can't be used again.
     */
    public void disable() {
        cardButton.setEnabled(false);
        matched = true;
    }

    public boolean isMatched() {
        return matched;
    }
}
