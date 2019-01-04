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
    AbstractGraphicPanel panel;
    private Board standardBoard;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String nick;
    private String gameMode;
    private int playerID;

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
        frame = new JFrame("Waiting For All Players To Connect");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1014,800);
        frame.setVisible(true);
        frame.setResizable(false);
        Thread receiverThread = new Thread(new statementReceiver());
        receiverThread.start();
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

    private void goToGame(int players,String gameMode)
    {
        BoardFactory hexBoardFactory= new HexBoardFactory();
        standardBoard = hexBoardFactory.getBoard(players,gameMode);
        PanelFactory panelFactory = new PanelFactory();
        panel = panelFactory.getPanel(gameMode,standardBoard,writer);
        //panel = new CircleGraphicPanel(standardBoard, writer);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setTitle(nick+"|"+PlayerColor.getColorName(playerID)+"|TURA PRZECIWNIKA");
        frame.repaint();
        frame.validate();
    }
    /*
    private void goToStandardGame(int players,String gamemode)
    {
        BoardFactory hexBoardFactory= new HexBoardFactory();
        standardBoard = hexBoardFactory.getBoard(players,gameMode);
        PanelFactory panelFactory = new PanelFactory();
        panel = panelFactory.getPanel(gameMode,standardBoard,writer);
        //panel = new CircleGraphicPanel(standardBoard, writer);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setTitle(nick+"|"+PlayerColor.getColorName(playerID)+"|TURA PRZECIWNIKA");
        frame.repaint();
        frame.validate();
    }
    */

    public class statementReceiver implements Runnable {
        public void run()
        {
            String wiadom;
            try {
                while((wiadom=reader.readLine())!=null) {
                    System.out.println("Odczytano " + wiadom);
                    String[] x = wiadom.split(" ");

                    if (x[0].equals("PLAYERMOVED"))
                    {
                        standardBoard.getFields()[Integer.parseInt(x[4])][Integer.parseInt(x[5])].setPlayer(
                                standardBoard.getFields()[Integer.parseInt(x[2])][Integer.parseInt(x[3])].getPlayer());
                        standardBoard.getFields()[Integer.parseInt(x[2])][Integer.parseInt(x[3])].setPlayer(0);
                        panel.repaint();
                        panel.validate();
                    }
                    else if(x[0].equals("ACCEPT"))
                    {
                        standardBoard.getFields()[Integer.parseInt(x[3])][Integer.parseInt(x[4])].setPlayer(
                                standardBoard.getFields()[Integer.parseInt(x[1])][Integer.parseInt(x[2])].getPlayer());
                        standardBoard.getFields()[Integer.parseInt(x[1])][Integer.parseInt(x[2])].setPlayer(0);
                        //setActive();
                        panel.getMyMouseAdapter().setActiveField(Integer.parseInt(x[3]),Integer.parseInt(x[4]));

                        panel.repaint();
                        panel.validate();
                    }
                    else if(x[0].equals("DECLINE"))
                    {
                        System.out.println("Zły ruch");
                        panel.repaint();
                        panel.validate();
                    }
                    else if(x[0].startsWith("YOURMOVE"))
                    {
                        panel.setMyTurn(true);
                        //frame.setTitle("TWOJA TURA - KOLOR: "+PlayerColor.getColor(playerID));
                        frame.setTitle(nick+"|"+PlayerColor.getColorName(playerID)+"|TWOJA TURA");
                    }
                    else if(x[0].startsWith("ENDMOVE"))
                    {
                        panel.setMyTurn(false);
                        frame.setTitle(nick+"|"+PlayerColor.getColorName(playerID)+"|TURA PRZECIWNIKA");
                    }
                    else if(x[0].startsWith("PLAYERQUIT"))
                    {
                        String uciekinier = x[1];
                        System.out.println("Gracz "+uciekinier + " wyszedł " + "koniec gry");
                        frame.setTitle("Gracz "+uciekinier + " wyszedł " + "koniec gry");
                        closeStreams();
                        break;
                        //TU KONIEC GRY
                    }
                    else if(x[0].startsWith("VICTORY"))
                    {
                        String zwyciezca = x[1];
                        System.out.println("Gracz "+zwyciezca + "wyszedł" + "koniec gry");
                        frame.setTitle("Gracz "+zwyciezca + " wyszedł " + "koniec gry");
                        closeStreams();
                        break;
                        //TU KONIEC GRY
                    }
                    else if(x[0].startsWith("GAME"))
                    {
                        int players=Integer.parseInt(x[2]);
                        gameMode =x[1];
                        gameMode ="sfd";
                        System.out.println("Liczba graczy "+players);
                        goToGame(players, gameMode);
                        panel.setPlayerID(playerID);
                        sleep(500);
                    }
                    else if(x[0].startsWith("YOURID"))
                    {
                        playerID = Integer.parseInt(x[1]);
                    }
                }
                socket.close();

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }
        public void closeStreams() throws IOException
        {
            reader.close();
            writer.close();
        }
    }
}

