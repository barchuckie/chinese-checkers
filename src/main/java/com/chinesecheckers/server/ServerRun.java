package com.chinesecheckers.server;

/**
 * Runs server window
 */
public class ServerRun {

    /**
     * Runs server window
     * @param args ignored
     */
    public static void main(String[] args) {
        try {
            ServerStartWindow gameServerWindow = new ServerStartWindow();
            gameServerWindow.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
