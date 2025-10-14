package com.mycompany.cblproject.Scenes;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * Class that creates Memory Scene
 */
public class MemoryScene {
    private static JPanel memoryPanel;

    public static void memoryPanel() {
        memoryPanel = new MemoryPanel();
        

        
        
    }

    public static JPanel getMemoryPanel() {
        return memoryPanel;
    }
}
