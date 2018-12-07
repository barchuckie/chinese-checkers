package com.chinesecheckers.client;

import javax.swing.*;
import java.awt.*;

public class BoardView {
    private JFrame frame;
    private JPanel mainPanel;

    public void start()
    {
        frame = new JFrame("Gra");
        mainPanel = new GraphicPanel();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(1014,800);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
