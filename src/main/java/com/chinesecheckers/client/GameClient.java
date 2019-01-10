package com.chinesecheckers.client;

import java.io.IOException;
import java.net.SocketException;

public interface GameClient {

    /**
     * Default client lifecycle
     */
    default void runClient() {
        String message;
        try {

            while((message = getMessage()) != null) {
                System.out.println("Odczytano " + message);
                String[] x = message.split(" ");

                if (x[0].equals("PLAYERMOVED")) {
                    onPlayerMoved(Integer.parseInt(x[2]), Integer.parseInt(x[3]), Integer.parseInt(x[4]),
                            Integer.parseInt(x[5]));
                } else if(x[0].equals("ACCEPT")) {
                    onAcceptMove(Integer.parseInt(x[1]), Integer.parseInt(x[2]), Integer.parseInt(x[3]),
                            Integer.parseInt(x[4]));
                } else if(x[0].equals("DECLINE")) {
                    onDeclineMove();
                } else if(x[0].startsWith("YOURMOVE")) {
                    onYourMove();
                } else if(x[0].startsWith("ENDMOVE")) {
                    onEndMove();
                } else if(x[0].startsWith("PLAYERQUIT")) {
                    onPlayerQuit(x[1]);
                    break;
                    //GAME OVER
                } else if(x[0].startsWith("VICTORY")) {
                    onVictory(x[1]);
                    break;
                    //GAME OVER
                } else if(x[0].startsWith("GAME")) {
                    onGame(x[1], Integer.parseInt(x[2]));
                } else if(x[0].startsWith("YOURID")) {
                    onYourID(Integer.parseInt(x[1]));
                }
            }
            onGameOver();
        } catch (SocketException ex) {
            System.out.println("Serwer rozłączony");
            System.exit(-1);
        } catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Handles "PLAYERMOVED" message.
     * Should update the board.
     * @param originalX original field x coordinate
     * @param originalY original field y coordinate
     * @param newX new field x coordinate
     * @param newY new field y coordinate
     */
    void onPlayerMoved(int originalX, int originalY, int newX, int newY);

    /**
     * Handles "ACCEPT" message. It is response to "CHECK".
     * Pawn should move on the given new field.
     * @param oldX old field x coordinate
     * @param oldY old field y coordinate
     * @param newX new field x coordinate
     * @param newY new field y coordinate
     */
    void onAcceptMove(int oldX, int oldY, int newX, int newY);

    /**
     * Handles "DECLINE" message. It is response to "CHECK".
     * Checked move was prohibited. Player shouldn't go on.
     */
    void onDeclineMove();

    /**
     * Handles "YOURMOVE" message. It is the player's turn now.
     */
    void onYourMove();

    /**
     * Handles "ENDMOVE" message. The player's turn has gone.
     */
    void onEndMove();

    /**
     * Handles "PLAYERQUIT" message.
     * The game should end now.
     * @param player player who has quited
     */
    void onPlayerQuit(String player);

    /**
     * Handles "VICTORY" message.
     * @param winner player who has won
     */
    void onVictory(String winner);

    /**
     * Handles "GAME" message.
     * Should do everything to start new game with given parameters.
     * @param gameMode game mode
     * @param numOfPLayers number of players playing the game
     */
    void onGame(String gameMode, int numOfPLayers);

    /**
     * Handles "YOURID" message.
     * @param ID defines which player you are in the game
     */
    void onYourID(int ID);

    /**
     * Should end the game.
     */
    void onGameOver();

    /**
     * Sends player's nick to the server
     * @param nick player's nick
     */
    void sendNickMessage(String nick);

    /**
     * Sends "CHECK" message to the server, which will response if this is correct move.
     * @param oldX old field x coordinate
     * @param oldY old field y coordinate
     * @param newX new field x coordinate
     * @param newY new field y coordinate
     */
    void sendCheckMessage(int oldX, int oldY, int newX, int newY);

    /**
     * Sends "MOVE" message to the server.
     * It means that player has moved and approved it.
     * @param originalX original field x coordinate
     * @param originalY original field y coordinate
     * @param newX new field x coordinate
     * @param newY new field y coordinate
     */
    void sendMoveMessage(int originalX, int originalY, int newX, int newY);

    /**
     * Sends "PASS" message to the server.
     * It means that player wants to pass turn. Coordinates of the original and new field should be the same.
     * @param originalX original field x coordinate
     * @param originalY original field y coordinate
     * @param newX new field x coordinate
     * @param newY new field y coordinate
     */
    void sendPassMessage(int originalX,int originalY,int newX,int newY);

    /**
     * Gets message. Usually, the message should be from socket input.
     * @return message
     * @throws IOException If an I/O error occurs.
     */
    String getMessage() throws IOException;
}
