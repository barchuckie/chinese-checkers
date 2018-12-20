package com.chinesecheckers.server;

import java.net.ServerSocket;
import java.util.ArrayList;

// Trzeba jakos zrobic żeby pierwszy klient hostował gre

public class GameServer {

    ServerSocket listener;
    static ArrayList outputSockets;  //nie wiem jak to zrobić bez statica


    public void start() throws Exception {
        listener = new ServerSocket(8901);
        outputSockets=new ArrayList();
        System.out.println("Chinese Checkers Server is Running");
        try {
            while (true) {
                //TODO: Server lifecycle
                Player playerA = new Player("Nick1",listener.accept());
                playerA.start();
                Player playerB = new Player("Nick2",listener.accept());
                playerB.start();
                outputSockets.add(playerA.getOutputStream());
                outputSockets.add(playerB.getOutputStream());
            }
        } finally {
            listener.close();
        }
    }
}
