package com.mycompany.cblproject.Scenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



/**
 * Class that creates Memory Scene
 * 
 */



public class MemoryScene {
    private static JPanel memoryPanel;

    // Setting the game up
    private static final int rows = 4;
    private static final int columns = 4;
    private static final int totalCards = rows * columns;

    private static JButton[] cards;
    private static Integer[] cardValues;
    private static ImageIcon cardBacks;
    private static ImageIcon[] cardFronts;

    private static JButton firstFlipped = null;
    private static JButton secondFlipped = null;
    private static boolean waiting = false;
    private static int matchesDone = 0;


    public static void memoryPanel() {
        memoryPanel = new JPanel(new BorderLayout (10,10));

        JLabel title = new JLabel("This is a memory game. Match all 12 pairs!", SwingConstants.CENTER);
        memoryPanel.add(title, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(rows, columns, 5, 5)) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(660, 660);
            }
        };

        memoryPanel.add(gridPanel, BorderLayout.CENTER);

        
        JButton backToMenu = new JButton("Return to menu");
        backToMenu.addActionListener(e -> Scene.startNewScene(GameMenu.getMenu()));
        memoryPanel.add(backToMenu, BorderLayout.SOUTH);

        loadImages(); //Method loading all images for the fronts and backs
    
        resetGame(gridPanel); // Sets up the cards in the grid

    }

    private static void loadImages() {
        cardBacks = loadAndScaleIcon("CBLProgramming/src/main/resources/sprites/images/BacksThis.png",100,100);

        cardFronts = new ImageIcon[12];
        for (int i = 0; i < 12; i++) {
            String path = "CBLProgramming/src/main/resources/sprites/images/cards/" + (i + 1) + ".png";
            cardFronts[i] = loadAndScaleIcon(path, 100, 100);

        }

    }

    private static ImageIcon loadAndScaleIcon(String path, int width, int height) {
        ImageIcon original = new ImageIcon(path);
        Image scaled = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);

    }

    // Generates a randomized array of card values

    private static Integer[] generateCardValues() {
        List<Integer> values = new ArrayList<>();
        for (int i = 1; i <= totalCards / 2; i++){
            values.add(i);
            values.add(i); // Because each value appears twice
        }

        //Randomizing every game at the start

        Collections.shuffle(values);
        return values.toArray(new Integer[0]);


    }


    private static void resetGame(JPanel gridPanel){
        gridPanel.removeAll(); // Clears previous cards
        matchesDone = 0;
        firstFlipped = null;
        secondFlipped = null;
        waiting = false;

        cards = new JButton[totalCards];
        cardValues = generateCardValues();

        for (int i = 0; i < totalCards; i++){
            JButton cardButton = new JButton();
            cardButton.setPreferredSize(new Dimension(100,100));
            cardButton.setIcon(cardBacks);
            cardButton.setBackground(Color.WHITE);
            cardButton.putClientProperty("index", i);

            cardButton.addActionListener(e -> onCardClick(cardButton));
            cards[i] = cardButton;
            gridPanel.add(cardButton);


        }

        gridPanel.revalidate();
        gridPanel.repaint();

        Timer startShowAll = new Timer(1000, e -> {
            for (JButton cardButton: cards) {
                cardButton.setIcon(cardBacks);

            }

        });

        startShowAll.setRepeats(false);
        startShowAll.start();

    }

    private static void onCardClick(JButton cardButton) {
        if (waiting || cardButton == firstFlipped || cardButton == secondFlipped) return;

        int index = (int) cardButton.getClientProperty("index");
        int value = cardValues[index];
        cardButton.setIcon(cardFronts[value -1]);

        if (firstFlipped == null) {
            firstFlipped = cardButton;

        } else if (secondFlipped == null) {
            secondFlipped = cardButton;
            checkMatch();


        }

    }

    private static void checkMatch() {
        int firstIndex = (int) firstFlipped.getClientProperty("index");
        int secondIndex = (int) secondFlipped.getClientProperty("index");
        int firstValue = cardValues[firstIndex];
        int secondValue = cardValues[secondIndex];

        if (firstValue == secondValue) {
            firstFlipped.putClientProperty("matched", true);
            secondFlipped.putClientProperty("matched", false);
            matchesDone++;
            resetSelection();

            if (matchesDone == totalCards/2){
                JOptionPane.showMessageDialog(memoryPanel, "Yay! You successfully matched all the pairs!");
            }


        } else{
            waiting = true;
            Timer timer = new Timer(1000, e -> {
                firstFlipped.setIcon(cardBacks);
                secondFlipped.setIcon(cardBacks);
                resetSelection();
                waiting = false;
            });
            timer.setRepeats(false);
            timer.start();

        }

    }


    private static void resetSelection() {
        firstFlipped = null;
        secondFlipped = null;
    

    }

    public static JPanel getMemoryPanel() {
        return memoryPanel;
    }
}
