package com.chinesecheckers.server;

import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.game.StandardGame;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;

class GameServer {

    private GameServerState state;

    private ServerSocket listener;
    private Player [] players;


    void start() throws Exception {
        state = new GameServerState();
        listener = new ServerSocket(8901);
        System.out.println("Chinese Checkers Server is Running");

        while (true) {
            // TODO: Server lifecycle

            state.setStateFree();
            Player gameCreator = new Player(listener.accept());

            /* Send state */
            gameCreator.sendMessage("STATE " + state.stateToString()); // STATE FREE

            /* Receive game params */
            String [] gameParams = gameCreator.read(); // GAME gameMode numOfPlayers
            if(!gameParams[0].equals("GAME")) {
                continue;
            }
            int numOfPlayers = Integer.parseInt(gameParams[2]);
            players = new Player[numOfPlayers];
            if (numOfPlayers > 0) {
                players[0] = gameCreator;
            } //else throw new Exception();

                /* TODO: Generowanie różnych trybów gry
                GameModeEnum gameMode;
                for(GameModeEnum mode : GameModeEnum.values()) {
                    if(mode.toString().equals(gameParams[1])) {
                        gameMode = mode;
                        break;
                    }
                }*/

            state.setStateWaiting();
            for(int i = 1; i < numOfPlayers; ++i) {
                players[i] = new Player(listener.accept());
                sendToEveryone("STATE " + state.stateToString()); // STATE WAITING
            }

            /* Create new game */
            GameData data = new GameData(numOfPlayers, players);
            Game game = new StandardGame(data);
            sendToEveryone("GAME " + gameParams[1] + " " + numOfPlayers);

            state.setStateInGame();
            sendToEveryone("STATE " + state.stateToString()); // STATE INGAME

            /* Lead the game */
            int currentPlayer;
            boolean playing = true;
            while(playing) {
                currentPlayer = game.getCurrentPlayer();
                String [] msg = players[currentPlayer].read();
                switch (msg[0]) {
                    case "MOVE": // MOVE oldX oldY newX newY
                        game.makeMove(players[currentPlayer], Integer.parseInt(msg[1]), Integer.parseInt(msg[2]),
                                Integer.parseInt(msg[3]), Integer.parseInt(msg[4]));
                        // PLAYER_MOVED playerNick oldX oldY newX newY
                        sendToEveryone("PLAYER_MOVED " + players[currentPlayer].getNick() +
                                Integer.parseInt(msg[1]) + " " + Integer.parseInt(msg[2]) + " " +
                                Integer.parseInt(msg[3]) + " " + Integer.parseInt(msg[4]) + " ");
                        break;

                    case "CHECK": // CHECK oldX oldY newX newY
                        if (game.validateMove(players[currentPlayer], Integer.parseInt(msg[1]), Integer.parseInt(msg[2]),
                                Integer.parseInt(msg[3]), Integer.parseInt(msg[4]))) {
                            players[currentPlayer].sendMessage("ACCEPT");
                        } else {
                            players[currentPlayer].sendMessage("DECLINE");
                        }
                        break;

                    case "ERROR":
                        playing = false;
                        sendToEveryone("PLAYERQUIT " + players[currentPlayer].getNick());
                        break;
                }
            }
        }
    }

    GameServerState getState() {
        return state;
    }

    public Player [] getPlayers() {
        return players;
    }

    public void sendToEveryone(String message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }
}
