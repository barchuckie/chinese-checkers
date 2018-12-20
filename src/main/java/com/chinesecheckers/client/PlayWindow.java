package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;
import com.chinesecheckers.client.Board.BoardFactory;
import com.chinesecheckers.client.Board.HexBoardFactory;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayWindow {
    private JFrame frame;
    private JPanel mainPanel;
    private int players;
    private int bots;
    private Board standardBoard;
    private Socket gniazdo;
    private BufferedReader reader;
    private PrintWriter writer;

    public PlayWindow(int players,int bots)
    {
        this.players=players;
        this.bots=bots;
        BoardFactory hexBoardFactory= new HexBoardFactory();
        standardBoard = hexBoardFactory.getBoard(players);
        configureCommunication();
    }

    public void start()
    {
        frame = new JFrame("Gra");
        mainPanel = new GraphicPanel(standardBoard, writer);
        Thread receiverThread = new Thread(new statementReceiver());
        receiverThread.start();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(1014,800);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void configureCommunication() {
        try {
            gniazdo = new Socket("127.0.0.1", 8901);
            InputStreamReader StreamReader = new InputStreamReader(gniazdo.getInputStream());
            reader = new BufferedReader(StreamReader);
            writer = new PrintWriter(gniazdo.getOutputStream());
            System.out.println("Obsluga sieci gotowa");
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    public class statementReceiver implements Runnable {
        public void run()
        {
            String wiadom;
            try
            {
                while (true)
                {
                    wiadom = reader.readLine();
                    System.out.println("Odczytano " + wiadom);
                    String[] x = wiadom.split(" ");
                    //System.out.println(x[0]);
                    if (x[0].equals("MOVE"))
                    {

                        standardBoard.getFields()[Integer.parseInt(x[3])][Integer.parseInt(x[4])].setPlayer(
                                standardBoard.getFields()[Integer.parseInt(x[1])][Integer.parseInt(x[2])].getPlayer());
                        standardBoard.getFields()[Integer.parseInt(x[1])][Integer.parseInt(x[2])].setPlayer(0);
                        mainPanel.repaint();
                    }
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }


        }
    }




}
