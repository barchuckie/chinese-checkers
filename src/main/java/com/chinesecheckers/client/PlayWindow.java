package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;
import com.chinesecheckers.client.Board.BoardFactory;
import com.chinesecheckers.client.Board.HexBoardFactory;

import javax.swing.*;
import java.awt.*;

public class PlayWindow {
    private JFrame frame;
    private JPanel mainPanel;
    private int players;
    private int bots;
    private Board standardBoard;

    public PlayWindow(int graczy,int bots)
    {
        this.players=graczy;
        this.bots=bots;
        BoardFactory hexBoardFactory= new HexBoardFactory();
        standardBoard = hexBoardFactory.getBoard(players);
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
