package com.chinesecheckers.client;

import javax.swing.*;
import java.awt.*;

public class PlayWindow {
    private JFrame frame;
    private JPanel mainPanel;
    private int players;
    private int bots;
    private StandardBoard standardBoard;

    public PlayWindow(int graczy,int bots)
    {
        this.players=graczy;
        this.bots=bots;
        standardBoard = new StandardBoard(graczy);

    }
    public void start()
    {
        frame = new JFrame("Gra");

        mainPanel = new GraphicPanel(standardBoard);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(1014,800);
        frame.setVisible(true);
        frame.setResizable(false);
    }


}
