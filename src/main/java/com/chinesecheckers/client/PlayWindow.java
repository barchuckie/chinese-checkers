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

/**
 * {@code PlayWindow} represents window with game.
 * It implements {@code GameClient} and handles all the actions between user and server.
 * @see GameClient
 */
public class PlayWindow implements GameClient {

    /**
     * Window content.
     */
    private JFrame frame;
    private AbstractGraphicPanel panel;
    private Board standardBoard;

    /**
     * Server communication variables.
     */
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    /**
     * Game variables.
     */
    private String nick;
    private String gameMode;
    private int playerID;
    private boolean myTurn = false;

    /**
     * Initializes new instance and sets up player's nick
     * @param nick player's nick
     */
    public PlayWindow(String nick) {
        this.nick = nick;
        try {
            sleep(10);
            configureCommunication();
            sleep(100);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sets up frame content and starts thread to communicate with server.
     */
    public void start() {
        frame = new JFrame("Waiting For All Players To Connect");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1014,800);
        frame.setVisible(true);
        frame.setResizable(false);
        Thread receiverThread = new Thread(new StatementReceiver());
        receiverThread.start();
    }

    /**
     * Configures communication with game server.
     */
    private void configureCommunication() {
        try {
            socket = new Socket("127.0.0.1", 8901);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(socket.getOutputStream(),true);
            sendNickMessage(nick);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Creates new board according to parameters and sets it up in frame.
     * @param players number of players in game
     * @param gameMode game mode
     */
    private void goToGame(int players,String gameMode) {
        BoardFactory hexBoardFactory= new HexBoardFactory();
        standardBoard = hexBoardFactory.getBoard(players, gameMode);
        PanelFactory panelFactory = new PanelFactory();
        panel = panelFactory.getPanel(gameMode, standardBoard, this);
        //panel = new CircleGraphicPanel(standardBoard, writer);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setTitle(nick+"|"+PlayerColor.getColorName(playerID)+"|TURA PRZECIWNIKA");
        frame.repaint();
        frame.validate();
    }

    /**
     * Moves any player's pawn from original field to the new one and updates panel.
     * @param originalX original field x coordinate
     * @param originalY original field y coordinate
     * @param newX new field x coordinate
     * @param newY new field y coordinate
     */
    @Override
    public void onPlayerMoved(int originalX, int originalY, int newX, int newY) {
        standardBoard.getFields()[newX][newY].setPlayer(standardBoard.getFields()[originalX][originalY].getPlayer());
        standardBoard.getFields()[originalX][originalY].setPlayer(0);
        panel.repaint();
        panel.validate();
    }

    /**
     * Moves the player from the old field to the new one
     * and set up mouse adapter to new field for further actions.
     * @param oldX old field x coordinate
     * @param oldY old field y coordinate
     * @param newX new field x coordinate
     * @param newY new field y coordinate
     */
    @Override
    public void onAcceptMove(int oldX, int oldY, int newX, int newY) {
        standardBoard.getFields()[newX][newY].setPlayer(standardBoard.getFields()[oldX][oldY].getPlayer());
        standardBoard.getFields()[oldX][oldY].setPlayer(0);
        //setActive();
        panel.getMyMouseAdapter().setActiveField(newX, newY);

        panel.repaint();
        panel.validate();
    }

    /**
     * Prints a proper message in the console.
     */
    @Override
    public void onDeclineMove() {
        System.out.println("Zły ruch");
        panel.repaint();
        panel.validate();
    }

    /**
     * Starts player's turn and updates frame title.
     */
    @Override
    public void onYourMove() {
        setMyTurn(true);
        //frame.setTitle("TWOJA TURA - KOLOR: "+PlayerColor.getColor(playerID));
        frame.setTitle(nick+"|"+PlayerColor.getColorName(playerID)+"|TWOJA TURA");
    }

    /**
     * Ends player's turn and updates frame title.
     */
    @Override
    public void onEndMove() {
        setMyTurn(false);
        frame.setTitle(nick+"|"+PlayerColor.getColorName(playerID)+"|TURA PRZECIWNIKA");
    }

    /**
     * Prints a proper message, updates frame title, ends the game and closes streams.
     * @param player player who has quited
     */
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

    /**
     * Starts new game with given parameters.
     * @param gameMode game mode
     * @param numOfPLayers number of players playing the game
     */
    @Override
    public void onGame(String gameMode, int numOfPLayers) {
        System.out.println("Liczba graczy " + numOfPLayers);
        goToGame(numOfPLayers, gameMode);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets player ID.
     * @param ID defines which player you are in the game
     */
    @Override
    public void onYourID(int ID) {
        playerID = ID;
    }

    /**
     * Closes socket.
     */
    @Override
    public void onGameOver() {
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNickMessage(String nick) {
        writer.println("NICK " + nick);
        System.out.println("NICK " + nick);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendCheckMessage(int oldX, int oldY, int newX, int newY) {
        writer.println("CHECK " + oldX + " " + oldY + " " + newX + " " + newY);
        System.out.println("sentMSG CHECK " + oldX + " " + oldY + " " + newX + " " + newY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMoveMessage(int originalX, int originalY, int newX, int newY) {
        writer.println("MOVE " + originalX + " " + originalY + " " + newX + " " + newY);
        System.out.println("sentMSG MOVE " + originalX + " " + originalY + " " + newX + " " + newY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPassMessage(int originalX,int originalY,int newX,int newY) {
        writer.println("PASS " + + originalX + " " + originalY + " " + newX + " " + newY);
        System.out.println("PASS");
    }

    /**
     * Gets message from the socket reader.
     * @return message
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public String getMessage() throws IOException {
        return reader.readLine();
    }

    /**
     * Closes streams.
     */
    private void closeStreams() {
        try {
            reader.close();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sets {@code myTurn} to true or false and prints a proper message in the console.
     * @param t value to set
     */
    private void setMyTurn(boolean t) {
        myTurn = t;
        System.out.println("Zmiana " + myTurn);
    }

    /**
     * Gets info if it is player's turn
     * @return true if it is player's turn, false if not
     */
    public boolean isMyTurn() {
        return myTurn;
    }

    /**
     * Gets player ID
     * @return player ID
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Gets graphic panel.
     */
    public AbstractGraphicPanel getPanel() {
        return panel;
    }

    /**
     * Ends player's turn and updates frame title. Similar to {@code onEndMove}.
     */
    public void onPassMessage() {
        setMyTurn(false);
        frame.setTitle(nick+"|"+PlayerColor.getColorName(playerID)+"|TURA PRZECIWNIKA");
    }

    /**
     * Represents thread running client communication with server.
     */
    public class StatementReceiver implements Runnable {

        /**
         * Runs the communication thread.
         */
        @Override
        public void run() {
            runClient();
        }

    }
}

