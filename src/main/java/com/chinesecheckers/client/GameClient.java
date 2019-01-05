package com.chinesecheckers.client;

import java.io.IOException;

public interface GameClient {

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void onPlayerMoved(int originalX, int originalY, int newX, int newY);

    void onAcceptMove(int oldX, int oldY, int newX, int newY);

    void onDeclineMove();

    void onYourMove();

    void onEndMove();

    void onPlayerQuit(String player);

    void onVictory(String winner);

    void onGame(String gameMode, int numOfPLayers);

    void onYourID(int ID);

    void onGameOver();

    String getMessage() throws IOException;
}
