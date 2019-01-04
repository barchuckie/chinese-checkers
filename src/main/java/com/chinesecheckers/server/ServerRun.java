package com.chinesecheckers.server;

public class ServerRun {
    public static void main(String[] args) {
        try {
            ServerStartWindow gameServerWindow = new ServerStartWindow();
            gameServerWindow.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
