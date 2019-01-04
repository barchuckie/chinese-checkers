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

public class PlayWindow implements GameClient {
    private JFrame frame;
    AbstractGraphicPanel panel;
    private Board standardBoard;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String nick;
    private String gameMode;
    private int playerID;

    public PlayWindow(String nick) {
        this.nick=nick;
        try {
            sleep(10);
            configureCommunication();
            sleep(100);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void start() {
        frame = new JFrame("Waiting For All Players To Connect");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1014,800);
        frame.setVisible(true);
        frame.setResizable(false);
        Thread receiverThread = new Thread(new StatementReceiver());
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

    private void goToGame(int players,String gameMode) {
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

    @Override
    public void onPlayerMoved(int originalX, int originalY, int newX, int newY) {
        standardBoard.getFields()[newX][newY].setPlayer(standardBoard.getFields()[originalX][originalY].getPlayer());
        standardBoard.getFields()[originalX][originalY].setPlayer(0);
        panel.repaint();
        panel.validate();
    }

    @Override
    public void onAcceptMove(int oldX, int oldY, int newX, int newY) {
        standardBoard.getFields()[newX][newY].setPlayer(standardBoard.getFields()[oldX][oldY].getPlayer());
        standardBoard.getFields()[oldX][oldY].setPlayer(0);
        //setActive();
        panel.getMyMouseAdapter().setActiveField(newX, newY);

        panel.repaint();
        panel.validate();
    }

    @Override
    public void onDeclineMove() {
        System.out.println("Zły ruch");
        panel.repaint();
        panel.validate();
    }

    @Override
    public void onYourMove() {
        panel.setMyTurn(true);
        //frame.setTitle("TWOJA TURA - KOLOR: "+PlayerColor.getColor(playerID));
        frame.setTitle(nick+"|"+PlayerColor.getColorName(playerID)+"|TWOJA TURA");
    }

    @Override
    public void onEndMove() {
        panel.setMyTurn(false);
        frame.setTitle(nick+"|"+PlayerColor.getColorName(playerID)+"|TURA PRZECIWNIKA");
    }

    @Override
    public void onPlayerQuit(String player) {
        System.out.println("Gracz " + player + " wyszedł " + "koniec gry");
        frame.setTitle("Gracz " + player + " wyszedł " + "koniec gry");
        closeStreams();
    }

    @Override
    public void onVictory(String winner) {
        System.out.println("Gracz "+ winner + " wygrał. " + "Koniec gry");
        frame.setTitle("Gracz "+ winner + " wygrał. " + " Koniec gry");
        closeStreams();
    }

    @Override
    public void onGame(String gameMode, int numOfPLayers) {
        System.out.println("Liczba graczy " + numOfPLayers);
        goToGame(numOfPLayers, gameMode);
        panel.setPlayerID(playerID);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onYourID(int ID) {
        playerID = ID;
    }

    @Override
    public void onGameOver() {
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getMessage() throws IOException {
        return reader.readLine();
    }

    public void closeStreams() {
        try {
            reader.close();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    public class StatementReceiver implements Runnable {

        @Override
        public void run() {
            runClient();
        }
    }
}

