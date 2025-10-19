package com.mycompany.cblproject.Scenes;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TicTacToeScene {

    private static JPanel ticTacToePanel;
    private static JButton[][] clickableGrid = new JButton[3][3];
    private static JLabel gameStatus;
    private static boolean finished = false;
    private static Random randomize = new Random();
    
    private static ImageIcon xs;
    private static ImageIcon os;


    public static void ticTacToePanel() {
        ticTacToePanel = new JPanel(new BorderLayout());

        xs = new ImageIcon(TicTacToeScene.class.getResource("/com/mycompany/resources/Xs_and_Os/redX.png"));
        os = new ImageIcon(TicTacToeScene.class.getResource("/com/mycompany/resources/Xs_and_Os/blueO.png"));


        JLabel info = new JLabel("This is Tic-Tac-Toe. Will you be able to beat the computer?", SwingConstants.CENTER);
        ticTacToePanel.add(info, BorderLayout.NORTH);

        JPanel boardGrid = new JPanel (new GridLayout(3,3));

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {

                JButton placingButton = new JButton();
                clickableGrid[i][j] = placingButton;

                int row = i;
                int column = j;

                placingButton.addActionListener(e -> reactToPlayerMove(row, column));
                boardGrid.add(placingButton);




            }

        }

        ticTacToePanel.add(boardGrid, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        gameStatus = new JLabel("Your turn, X!", SwingConstants.CENTER);
        bottomPanel.add(gameStatus, BorderLayout.CENTER);

        JButton menuButton = new JButton("Return to menu!");
        menuButton.addActionListener(e -> Scene.startNewScene(GameMenu.getMenu()));
        bottomPanel.add(menuButton);

        ticTacToePanel.add(bottomPanel,BorderLayout.SOUTH);

        
    }

    private static void reactToPlayerMove(int row, int column){
        if (finished || clickableGrid[row][column].getIcon() != null){
            return;
        }

        clickableGrid[row][column].setIcon(xs);
        clickableGrid[row][column].setEnabled(false);

        if(checkWin(xs)) {
            gameStatus.setText("You have won!");
            endGame();
            return;

        } else if (fullBoard()) {
            gameStatus.setText("It's a draw.");
            endGame();
            return;
        }

        computerMoves();

    }

    private static void computerMoves() {
        
        if (finished){
            return;
        }
        
        List<int[]> emptyCells = new ArrayList<>();
            
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++){
                if(clickableGrid[i][j].getIcon() == null) {
                    emptyCells.add(new int[]{i,j});
                }

            }
        }
            
        if(emptyCells.isEmpty()) {
            return;

        }

        int[] move = emptyCells.get(randomize.nextInt(emptyCells.size()));
        int row = move[0];
        int column = move[1];

        clickableGrid[row][column].setIcon(os);

       
        if (checkWin(os)) {
            gameStatus.setText("Computer wins!");
            endGame();
        } else if (fullBoard()) {
            gameStatus.setText("It's a draw.");
            endGame();
        } else {
            gameStatus.setText("Your turn, X!");
        }
    }


        



    private static boolean checkWin(ImageIcon icon) {

        // Checking for a win in the rows and columns

        for(int i = 0; i < 3; i++){
            if (sameIcon(clickableGrid[i][0], clickableGrid[i][1], clickableGrid[i][2], icon)){
                return true;
            }
            if (sameIcon(clickableGrid[0][i], clickableGrid[1][i], clickableGrid[2][i], icon)){
                return true;
            }
        }

        //Checking for a win diagonally

        if (sameIcon(clickableGrid[0][0], clickableGrid[1][1], clickableGrid[2][2], icon)){
            return true;
        }
        if (sameIcon(clickableGrid[0][2], clickableGrid[1][1], clickableGrid[2][0], icon)){
            return true;
        }

        return false;
    }

    private static boolean sameIcon(JButton a, JButton b, JButton c, ImageIcon icon) {
        return a.getIcon() == icon && b.getIcon() == icon && c.getIcon() == icon;
    }


    private static boolean fullBoard() {

        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (clickableGrid[i][j].getIcon() == null) {
                    return false;
                }

            }
        }

        return true;

    }

    private static void endGame() {
        finished = true;

        for (int i =0; i < 3; i++){
            for (int j =0; j < 3; j++) {
                clickableGrid[i][j].setEnabled(false);

            }
         }

    }


    public static JPanel getTicTacToePanel() {
        return ticTacToePanel;
    }
} 

