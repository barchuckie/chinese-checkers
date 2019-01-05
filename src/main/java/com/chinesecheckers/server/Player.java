package com.chinesecheckers.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
    private String nick;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private boolean finished;

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

    public Player(String nick) {
        this.nick = nick;
        finished = false;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String [] read() {
        String [] msg;
        try {
            msg = input.readLine().split(" ");
        } catch (Exception e) {
            msg =  new String[]{ "ERROR", "PLAYERQUIT" };
        }
        return msg;
    }

    public void sendMessage(String message) {
        output.println(message);
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}
