package com.chinesecheckers.server;

import javax.security.auth.RefreshFailedException;
import java.net.ServerSocket;
import java.util.ArrayList;

// Trzeba jakos zrobic żeby pierwszy klient hostował gre

class GameServer {

    enum GameServerState {
        FREE,
        WAITING,
        INGAME
    }

    private GameServerState state;

    private ServerSocket listener;
    private ArrayList outputSockets;  //nie wiem jak to zrobić bez statica


    void start() throws Exception {
        listener = new ServerSocket(8901);
        outputSockets = new ArrayList();
        System.out.println("Chinese Checkers Server is Running");
        try {
            while (true) {
                // TODO: Server lifecycle

                /* Player playerA = new Player("Nick1",listener.accept());
                playerA.start();
                Player playerB = new Player("Nick2",listener.accept());
                playerB.start();
                outputSockets.add(playerA.getOutputStream());
                outputSockets.add(playerB.getOutputStream()); */

                state = GameServerState.FREE;
                Player gameCreator = new Player(listener.accept(), this);
                /*
                 * Send state
                 * Receive game params
                 * Create new game
                 */
                state = GameServerState.WAITING;
                /*
                 * Waiting for players to connect
                 */
                state = GameServerState.INGAME;
                /*
                 * Lead the game
                 */
            }
        } finally {
            listener.close();
        }
    }

    GameServerState getState() {
        return state;
    }

    public ArrayList getOutputSockets() {
        return outputSockets;
    }
}
