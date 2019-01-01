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
    private GraphicPanel graphicPanel;
    private Board standardBoard;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String nick;
    private String tryb;

    public PlayWindow(String nick)
    {
        this.nick=nick;
        try{
            sleep(10);
            configureCommunication();
            sleep(100);
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
            writer.println("NICK " + nick);
            System.out.println("NICK" + nick);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private void goToGame(int players,String tryb)
    {
        if(tryb.equals("STANDARD"))
        {
            goToStandardGame(players,tryb);
        }
    }

    private void goToStandardGame(int players,String tryb)
    {
        BoardFactory hexBoardFactory= new HexBoardFactory();
        standardBoard = hexBoardFactory.getBoard(players);
        graphicPanel = new GraphicPanel(standardBoard, writer);
        frame.getContentPane().add(BorderLayout.CENTER, graphicPanel);
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
                    //pierwsza wiadomosc to GAME tryb liczbagraczy
                    wiadom = reader.readLine();
                    System.out.println("Odczytano " + wiadom);
                    String[] x = wiadom.split(" ");

                    if (x[0].equals("PLAYERMOVED"))
                    {
                        standardBoard.getFields()[Integer.parseInt(x[4])][Integer.parseInt(x[5])].setPlayer(
                                standardBoard.getFields()[Integer.parseInt(x[2])][Integer.parseInt(x[3])].getPlayer());
                        standardBoard.getFields()[Integer.parseInt(x[2])][Integer.parseInt(x[3])].setPlayer(0);
                        graphicPanel.repaint();
                    }
                    else if(x[0].equals("ACCEPT"))
                    {
                        standardBoard.getFields()[Integer.parseInt(x[3])][Integer.parseInt(x[4])].setPlayer(
                                standardBoard.getFields()[Integer.parseInt(x[1])][Integer.parseInt(x[2])].getPlayer());
                        standardBoard.getFields()[Integer.parseInt(x[1])][Integer.parseInt(x[2])].setPlayer(0);
                        graphicPanel.repaint();
                    }
                    else if(x[0].equals("DECLINE"))
                    {
                        System.out.println("Zły ruch");
                    }
                    else if(x[0].startsWith("YOURMOVE"))
                    {
                        graphicPanel.setMyTurn(true);
                        frame.setTitle("TWOJA TURA");
                    }
                    else if(x[0].startsWith("NOT"))
                    {
                        graphicPanel.setMyTurn(false);
                        frame.setTitle("TURA PRZECIWNIKA");
                    }
                    else if(x[0].startsWith("PLAYERQUIT"))
                    {
                        String uciekinier = x[1];
                        System.out.println("Gracz "+uciekinier + "wyszedł" + "koniec gry");
                        //TU KONIEC GRY
                    }
                    else if(x[0].startsWith("VICTORY"))
                    {
                        String zwyciezca = x[1];
                        System.out.println("Gracz "+zwyciezca + "wyszedł" + "koniec gry");
                        //TU KONIEC GRY
                    }
                    else if(x[0].startsWith("GAME"))
                    {
                        int players=Integer.parseInt(x[2]);
                        tryb=x[1];
                        System.out.println("Liczba graczy "+players);
                        goToGame(players,tryb);
                        sleep(500);
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
