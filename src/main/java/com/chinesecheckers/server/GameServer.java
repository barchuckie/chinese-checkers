package com.chinesecheckers.server;

import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.game.StandardGame;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;

public class GameServer {

    private ServerSocket listener;
    private Player [] players;
    private int numOfPlayers;
    private int numOfBots;
    private GameModeEnum gameMode;

    public GameServer(int numOfPlayers, int numOfBots, GameModeEnum gameMode) {
        this.numOfPlayers = numOfPlayers;
        this.numOfBots = numOfBots;
        this.gameMode = gameMode;
    }

    public void start() throws Exception {
        listener = new ServerSocket(8901);
        System.out.println("Chinese Checkers Server is Running");

        //while (true) {

            /*state.setStateFree();
            Player gameCreator = new Player(listener.accept());

            /* Send state
            gameCreator.sendMessage("STATE " + state.stateToString()); // STATE FREE

            /* Receive game params
            String [] gameParams = gameCreator.read(); // GAME gameMode numOfPlayers
            if(!gameParams[0].equals("GAME")) {
                continue;
            }
            int numOfPlayers = Integer.parseInt(gameParams[2]);
            players = new Player[numOfPlayers];
            if (numOfPlayers > 0) {
                players[0] = gameCreator;
            } //else throw new Exception();

                GameModeEnum gameMode;
                for(GameModeEnum mode : GameModeEnum.values()) {
                    if(mode.toString().equals(gameParams[1])) {
                        gameMode = mode;
                        break;
                    }
                }*/
            players = new Player[numOfPlayers];
            for(int i = 0; i < numOfPlayers; ++i) {
                players[i] = new Player(listener.accept());
                System.out.println("listener accepted");
                String [] nickMsg = players[i].read();
                if(!nickMsg[0].equals("NICK") || nickMsg.length < 2) {
                    i--;
                    continue;
                }
                players[i].setNick(nickMsg[1]);
                players[i].sendMessage("YOURID " + (i+1));
            }

            /* Create new game */
            GameData data = new GameData(numOfPlayers, players);
            Game game = new StandardGame(data);
            sendToEveryone("GAME " + gameMode.toString() + " " + numOfPlayers);

            /* Lead the game */
            int currentPlayer;
            boolean playing = true;
            while(playing) {
                currentPlayer = game.getCurrentPlayer();
                players[currentPlayer].sendMessage("YOURMOVE");
                String [] msg = players[currentPlayer].read();
                switch (msg[0]) {
                    case "MOVE": // MOVE oldX oldY newX newY
                        game.makeMove(players[currentPlayer], Integer.parseInt(msg[1]), Integer.parseInt(msg[2]),
                                Integer.parseInt(msg[3]), Integer.parseInt(msg[4]));
                        // PLAYER_MOVED playerNick oldX oldY newX newY
                        sendToEveryone("PLAYERMOVED " + players[currentPlayer].getNick() + " " +
                                Integer.parseInt(msg[1]) + " " + Integer.parseInt(msg[2]) + " " +
                                Integer.parseInt(msg[3]) + " " + Integer.parseInt(msg[4]) + " ");
                        if(game.checkWinner(currentPlayer)) {
                            sendToEveryone("VICTORY " + players[currentPlayer].getNick());
                        }
                        break;

                    case "CHECK": // CHECK oldX oldY newX newY
                        if (game.validateMove(players[currentPlayer], Integer.parseInt(msg[1]), Integer.parseInt(msg[2]),
                                Integer.parseInt(msg[3]), Integer.parseInt(msg[4]))) {
                            players[currentPlayer].sendMessage("ACCEPT "+Integer.parseInt(msg[1]) +" "+ Integer.parseInt(msg[2])+
                                    " "+Integer.parseInt(msg[3]) +" "+ Integer.parseInt(msg[4]));
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
            listener.close();
        //}
    }

    public Player [] getPlayers() {
        return players;
    }

    private void sendToEveryone(String message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }
}
