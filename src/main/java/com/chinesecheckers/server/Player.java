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

    public Player(Socket socket) {
        this.socket = socket;
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
        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
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

    public String [] read() {
        //TODO: read from input
        String [] msg;
        try {
            msg = input.readLine().split(" ");
        } catch (IOException e) {
            msg =  new String[]{"ERROR", "PLAYERQUIT"};
        }
        return msg;
    }

    public void sendMessage(String message) {
        output.println(message);
    }
}
