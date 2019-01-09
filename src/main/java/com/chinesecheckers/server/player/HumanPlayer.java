package com.chinesecheckers.server.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HumanPlayer extends Player {

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
     * Instantiate Player with given socket
     * @param socket socket to connect with client
     */
    public HumanPlayer(Socket socket) {
        this.socket = socket;
        super.setFinished(false);
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
    public HumanPlayer(String nick, Socket socket) {
        super.setNick(nick);
        this.socket = socket;
        super.setFinished(false);
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
    public HumanPlayer(String nick) {
        super.setNick(nick);
        super.setFinished(false);
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
     * {@inheritDoc}
     * @return false
     */
    @Override
    public boolean isBot() {
        return false;
    }
}
