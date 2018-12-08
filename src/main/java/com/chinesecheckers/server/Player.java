package com.chinesecheckers.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread {
    private String nick;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public Player(String nick, Socket socket) {
        this.nick = nick;
        this.socket = socket;

        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME " + nick);
            output.println("MESSAGE Waiting for opponents to connect");
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void run() {
        /*try {
            // The thread is only started after everyone connects.
            output.println("MESSAGE All players connected");

            // Tell the first player that it is her turn.
            if (nick.equals("X")) {
                output.println("MESSAGE Your move");
            }

            // Repeatedly get commands from the client and process them.
            while (true) {
                //TODO: Client listener
            }
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }*/
    }
}
