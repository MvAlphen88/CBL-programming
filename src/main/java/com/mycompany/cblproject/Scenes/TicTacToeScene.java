package com.mycompany.cblproject.Scenes;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Class that creates and controls the Tic-Tac-Toe game.
 * @author Camila
 */
public class TicTacToeScene {

    private static JPanel ticTacToePanel;
    private static JButton[][] clickableGrid = new JButton[3][3];
    private static boolean finished = false;
    private static Random randomize = new Random();
    
    private static ImageIcon xs;
    private static ImageIcon os;

    /**
     * Method that creates the panel tic tac toe is played in.
     */
    public static void ticTacToePanel() {
        ticTacToePanel = new JPanel(new BorderLayout());

        String xPath = "/com/mycompany/resources/Xs_and_Os/redX.png";
        String oPath = "/com/mycompany/resources/Xs_and_Os/blueO.png";

        ImageIcon rawX = new ImageIcon(TicTacToeScene.class.getResource(xPath));
        ImageIcon rawO = new ImageIcon(TicTacToeScene.class.getResource(oPath));

        Image scaledX = rawX.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image scaledO = rawO.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        xs = new ImageIcon(scaledX);
        os = new ImageIcon(scaledO);

        String infoString = "This is Tic-Tac-Toe. Will you be able to beat the computer?";
        JLabel info = new JLabel(infoString, SwingConstants.CENTER);
        ticTacToePanel.add(info, BorderLayout.NORTH);

        JPanel boardGrid = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
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

        JButton menuButton = new JButton("Return to menu!");
        menuButton.addActionListener(e -> Scene.startNewScene(GameMenu.getMenu()));
        bottomPanel.add(menuButton);

        ticTacToePanel.add(bottomPanel, BorderLayout.SOUTH);

        
    }

    private static void reactToPlayerMove(int row, int column) {
        if (finished || clickableGrid[row][column].getIcon() != null) {
            return;
        }

        clickableGrid[row][column].setIcon(xs);
    

        if (checkWin(xs)) {
            showEndMessage("You have won!");
            return;

        } else if (fullBoard()) {
            showEndMessage("It's a draw.");
            return;
        }

        new javax.swing.Timer(700, e -> {
            computerMoves();
            ((javax.swing.Timer) e.getSource()).stop();
        }).start();

    }


    private static void computerMoves() {
        
        if (finished) {
            return;
        }
        
        // Look for any empty cells,  and if the list is empty there are no moves left
        List<int[]> emptyCells = getEmptyCells();   
        if (emptyCells.isEmpty()) {
            return;
        }

        if (tryWinningMove(emptyCells)) {
            return;
        }

        if (tryBlockingMove(emptyCells)) {
            return;
        }

        makeRandomMove(emptyCells);

    }

    private static boolean tryWinningMove(List<int[]> emptyCells) {
        int[] move = findingBestMove(emptyCells, os);
        return attemptMove(move, os, "Sorry, the computer has won!");

    }
    
    private static boolean tryBlockingMove(List<int[]> emptyCells) {
        int[] move = findingBestMove(emptyCells, xs);
        return attemptMove(move, os, null);
    }

    private static void makeRandomMove(List<int[]> emptyCells) {
        int[] move = emptyCells.get(randomize.nextInt(emptyCells.size()));
        makeMove(move, os, null);
    
        if (checkWin(os)) {
            showEndMessage("Sorry, the computer has won!");

        } else if (fullBoard()) {
            showEndMessage("It's a draw!");

        }

    }

    private static boolean attemptMove(int[] move, ImageIcon icon, String endMessage) {

        if (move == null) {
            return false;
        }

        makeMove(move, icon, endMessage);
        return true;
    }

    private static List<int[]> getEmptyCells() {
        List<int[]> emptyCells = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (clickableGrid[i][j].getIcon() == null) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }

        return emptyCells;
    }

    private static int[] findingBestMove(List<int[]> emptyCells, ImageIcon testIcon) {
        
        for (int[] cell : emptyCells) {
            int r = cell[0];
            int c = cell[1];

            //Check whether placing an icon would cause a win, and if so, place there

            clickableGrid[r][c].setIcon(testIcon);
            boolean win = checkWin(testIcon);
            clickableGrid[r][c].setIcon(null);

            if (win) {
                return cell;

            } 

        }
        return null; 

    }

    private static void makeMove(int[] move, ImageIcon icon, String endMessage) {
        int r = move[0];
        int c = move[1];
        clickableGrid[r][c].setIcon(icon);

        if (endMessage != null) {
            showEndMessage(endMessage);

        }
    }

    private static void showEndMessage(String message) {
        finished = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clickableGrid[i][j].setEnabled(false);
            }
        }

       
        JOptionPane.showMessageDialog(ticTacToePanel,
            message, "Game over!", 
            JOptionPane.INFORMATION_MESSAGE);

        resetGame();

    }
        



    private static boolean checkWin(ImageIcon icon) {
        // Checking for a win in the rows and columns

        for (int i = 0; i < 3; i++) {
            if (rowWin(i, icon) || colWin(i, icon)) {
                return true;

            }
        }

        // or diagonally

        return diagWin(icon);
    }

    // Methods checking winning combinations of icons

    private static boolean rowWin(int row, ImageIcon icon) {
        return sameIcon(clickableGrid[row][0], clickableGrid[row][1], clickableGrid[row][2], icon);

    }

    private static boolean colWin(int col, ImageIcon icon) {
        return sameIcon(clickableGrid[0][col], clickableGrid[1][col], clickableGrid[2][col], icon);

    }

    private static boolean diagWin(ImageIcon icon) {
        return sameIcon(clickableGrid[0][0], clickableGrid[1][1], clickableGrid[2][2], icon)
            || sameIcon(clickableGrid[0][2], clickableGrid[1][1], clickableGrid[2][0], icon);
    }


    private static boolean sameIcon(JButton a, JButton b, JButton c, ImageIcon icon) {
        return a.getIcon() == icon && b.getIcon() == icon && c.getIcon() == icon;
    }


    private static boolean fullBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clickableGrid[i][j].getIcon() == null) {
                    return false;
                }

            }
        }

        return true;
    }

    private static void resetGame() {
        finished = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clickableGrid[i][j].setIcon(null);
                clickableGrid[i][j].setEnabled(true);
            }
        }
    }

    public static JPanel getTicTacToePanel() {
        return ticTacToePanel;
    }
} 

