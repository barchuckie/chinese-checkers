package com.chinesecheckers.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class representing GameClient in a game
 */
public class Player {
    /**
     * Player's nick
     */
    private String nick;

    /**
     * Socket to be listen on
     */
    private Socket socket;

    /**
     * Input from socket
     */
    private BufferedReader input;
    /**
     * Output to socket
     */
    private PrintWriter output;
    /**
     * Determines whether player has finished a game
     */
    private boolean finished;

    /**
     * Instantiate Player with given socket
     * @param socket socket to connect with client
     */
    public Player(Socket socket) {
        this.socket = socket;
        finished = false;
        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }

    /**
     * Instantiate Player with given nick and socket
     * @param nick player's nick
     * @param socket socket to connect with client
     */
    public Player(String nick, Socket socket) {
        this.nick = nick;
        this.socket = socket;
        finished = false;
        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }

    /**
     * Instantiate Player with given nick
     * @param nick player's nick
     */
    public Player(String nick) {
        this.nick = nick;
        finished = false;
    }

    /**
     * Gets player's nick
     * @return player's nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * Sets player's nick
     * @param nick player's nick
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Reads socket input and converts it into message which is an array of String
     * @return message
     */
    public String [] read() {
        String [] msg;
        try {
            msg = input.readLine().split(" ");
        } catch (Exception e) {
            msg =  new String[]{ "ERROR", "PLAYERQUIT" };
        }
        return msg;
    }

    /**
     * Sends message to the client
     * @param message message to send
     */
    public void sendMessage(String message) {
        output.println(message);
    }

    /**
     * Gets {@code finished} value
     * @return true if player has finished, false if not
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Sets {@code finished} value
     * @param finished value to be set
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}
