package com.chinesecheckers.server;

import java.net.ServerSocket;

public class GameServer {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Chinese Checkers Server is Running");
        try {
            while (true) {
                //TODO: Server lifecycle
            }
        } finally {
            listener.close();
        }
    }

}
