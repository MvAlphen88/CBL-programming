package com.mycompany.cblproject.Games;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MemoryGame {
    private final int ROWS = 4;
    private final int COLUMNS = 4;
    private final int TOTAL_CARDS = ROWS * COLUMNS;

    private MemoryCards[] memoryCards;
    private ImageIcon cardBacks;
    private ImageIcon[] cardFronts;

    private MemoryCards firstSelected = null;
    private MemoryCards secondSelected = null;
    private boolean isChecking = false;

    /**
     * Creates and returns the memory game grid panel.
     */
    public JPanel createMemoryGame() {
        loadImages();
        JPanel gridPanel = new JPanel(new GridLayout(ROWS, COLUMNS, 5, 5)) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(660, 660);
            }
        };

        memoryCards = new MemoryCards[TOTAL_CARDS];
        Integer[] cardValues = generateCardValues();

        for (int i = 0; i < TOTAL_CARDS; i++) {
            int value = cardValues[i];
            ImageIcon frontImage = cardFronts[value - 1]; // Assuming values are 1-based

            MemoryCards card = new MemoryCards(value, frontImage, cardBacks);
            memoryCards[i] = card;

            JButton button = card.getButton();
            int index = i;

            button.addActionListener(e -> handleCardClick(index));
            gridPanel.add(button);
        }

        return gridPanel;
    }

    private void loadImages() {
        String cardBackPath = "src/main/java/com/mycompany/resources/cardbacks/backs.png";
        cardBacks = loadAndScaleIcon(cardBackPath, 100, 100);

        cardFronts = new ImageIcon[12];
        for (int i = 0; i < 12; i++) {
            String path = "src/main/java/com/mycompany/resources/cards/" + (i + 1) + ".png";
            cardFronts[i] = loadAndScaleIcon(path, 100, 100);

        }

    }

    private ImageIcon loadAndScaleIcon(String path, int width, int height) {
        ImageIcon original = new ImageIcon(path);
        Image scaled = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);

    }

    private Integer[] generateCardValues() {
        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 1; i <= TOTAL_CARDS / 2; i++) {
            values.add(i);
            values.add(i); // each pair appears twice
        }
        Collections.shuffle(values);
        return values.toArray(new Integer[0]);
    }

    private void handleCardClick(int index) {
        if (isChecking || memoryCards[index].isMatched()) return;

        MemoryCards clicked = memoryCards[index];
        clicked.showFront();

        if (firstSelected == null) {
            firstSelected = clicked;
        } else if (secondSelected == null && clicked != firstSelected) {
            secondSelected = clicked;
            isChecking = true;

            Timer timer = new Timer(1000, e -> checkMatch());
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void checkMatch() {
        if (firstSelected.getValue() == secondSelected.getValue()) {
            firstSelected.disable();
            secondSelected.disable();
        } else {
            firstSelected.showBack();
            secondSelected.showBack();
        }

        firstSelected = null;
        secondSelected = null;
        isChecking = false;

        if (checkWin()) {
            JOptionPane.showMessageDialog(null, "You matched all the cards!");
        }
    }

    private boolean checkWin() {
        for (MemoryCards card : memoryCards) {
            if (!card.isMatched()) {
                return false;
            }
        }
        return true;
    }
}
