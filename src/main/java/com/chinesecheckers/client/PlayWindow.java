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

import static java.lang.Thread.sleep;

public class PlayWindow {
    private JFrame frame;
    private GraphicPanel mainPanel;
    private Board standardBoard;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public PlayWindow()
    {
        configureCommunication();
        try{
            sleep(10);
        }catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }

    public void start()
    {
        Thread receiverThread = new Thread(new statementReceiver());
        receiverThread.start();
        frame = new JFrame("Waiting For All Players To Connect");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1014,800);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void configureCommunication() {
        try {
            socket = new Socket("127.0.0.1", 8901);
            InputStreamReader StreamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(StreamReader);
            writer = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("Obsluga sieci gotowa");
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private void goToGame(int players)
    {
        BoardFactory hexBoardFactory= new HexBoardFactory();
        standardBoard = hexBoardFactory.getBoard(players);
        mainPanel = new GraphicPanel(standardBoard, writer);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setTitle("Gra");
        frame.repaint();
    }
    public class statementReceiver implements Runnable {
        public void run()
        {
            String wiadom;
            try
            {
                while (true)
                {
                    sleep(1000);
                    System.out.println(1000);
                    /*
                    wiadom = reader.readLine();
                    System.out.println("Odczytano " + wiadom);
                    String[] x = wiadom.split(" ");
                    if (x[0].equals("MOVE"))
                    {
                        standardBoard.getFields()[Integer.parseInt(x[3])][Integer.parseInt(x[4])].setPlayer(
                                standardBoard.getFields()[Integer.parseInt(x[1])][Integer.parseInt(x[2])].getPlayer());
                        standardBoard.getFields()[Integer.parseInt(x[1])][Integer.parseInt(x[2])].setPlayer(0);
                        mainPanel.repaint();
                    }
                    else if(x[0].startsWith("YOUR"))
                    {
                        mainPanel.setMyTurn(true);
                        frame.setTitle("TWOJA TURA");
                    }
                    else if(x[0].startsWith("NOT"))
                    {
                        mainPanel.setMyTurn(false);
                        frame.setTitle("TURA PRZECIWNIKA");
                    }
                    else if(x[0].startsWith("INFO"))
                    {
                        int players=Integer.parseInt(x[1]);
                        int bots=Integer.parseInt(x[2]);
                        System.out.println("Liczba graczy "+players+" w tym botow "+bots);
                        //goToGame(players);
                        goToGame(2);
                        sleep(500);
                    }
                    */
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
