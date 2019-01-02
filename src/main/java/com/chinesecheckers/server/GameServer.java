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
    private Game game;
    private GameData data;

    public GameServer(int numOfPlayers, int numOfBots, GameModeEnum gameMode) {
        this.numOfPlayers = numOfPlayers;
        this.numOfBots = numOfBots;
        this.gameMode = gameMode;
    }

    public void start() throws Exception {
        listener = new ServerSocket(8901);
        System.out.println("Chinese Checkers Server is Running");

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
        data = new GameData(numOfPlayers, players);
        game = new StandardGame(data);
        sendToEveryone("GAME " + gameMode.toString() + " " + numOfPlayers);

        /* Lead the game */
        int currentPlayer;
        boolean playing = true;
        while(true) {
            currentPlayer = game.getCurrentPlayer();
            players[currentPlayer].sendMessage("YOURMOVE");
            String [] msg = players[currentPlayer].read();
            System.out.print("Otrzymano: ");
            for(String czesc : msg) System.out.print(czesc + " ");
            System.out.println();
            // MOVE oldX oldY newX newY
            if ("MOVE".equals(msg[0])) {
                System.out.println("Wybrano: MOVE");
                game.makeMove(players[currentPlayer], Integer.parseInt(msg[1]), Integer.parseInt(msg[2]),
                        Integer.parseInt(msg[3]), Integer.parseInt(msg[4]));

                // PLAYER_MOVED playerNick oldX oldY newX newY
                sendToEveryoneExceptCurrent("PLAYERMOVED " + players[currentPlayer].getNick() + " " +
                        Integer.parseInt(msg[1]) + " " + Integer.parseInt(msg[2]) + " " +
                        Integer.parseInt(msg[3]) + " " + Integer.parseInt(msg[4]) + " ", players[currentPlayer]);
                        /*if(game.checkWinner(currentPlayer)) {
                            sendToEveryone("VICTORY " + players[currentPlayer].getNick());
                        }*/
            } else if ("CHECK".equals(msg[0])) {
                System.out.println("Wybrano: CHECK");
                if (game.validateMove(players[currentPlayer], Integer.parseInt(msg[1]), Integer.parseInt(msg[2]),
                        Integer.parseInt(msg[3]), Integer.parseInt(msg[4]))) {
                    System.out.println("Ruch poprawny");
                    players[currentPlayer].sendMessage("ACCEPT " + Integer.parseInt(msg[1]) + " " + Integer.parseInt(msg[2]) +
                            " " + Integer.parseInt(msg[3]) + " " + Integer.parseInt(msg[4]));
                } else {
                    System.out.println("Ruch NIE poprawny");
                    players[currentPlayer].sendMessage("DECLINE");
                }
                System.out.println("Dotarłem za if");
            } else if ("ERROR".equals(msg[0])) {
                System.out.println("Wybrano: ERROR");
                //playing = false;
                sendToEveryone("PLAYERQUIT " + players[currentPlayer].getNick());
                break;
            }
            /*switch (msg[0]) {
                case "MOVE": // MOVE oldX oldY newX newY
                    System.out.println("Wybrano: MOVE");
                    game.makeMove(players[currentPlayer], Integer.parseInt(msg[1]), Integer.parseInt(msg[2]),
                            Integer.parseInt(msg[3]), Integer.parseInt(msg[4]));

                    // PLAYER_MOVED playerNick oldX oldY newX newY
                    sendToEveryoneExceptCurrent("PLAYERMOVED " + players[currentPlayer].getNick() + " " +
                            Integer.parseInt(msg[1]) + " " + Integer.parseInt(msg[2]) + " " +
                            Integer.parseInt(msg[3]) + " " + Integer.parseInt(msg[4]) + " ",players[currentPlayer]);
                        /*if(game.checkWinner(currentPlayer)) {
                            sendToEveryone("VICTORY " + players[currentPlayer].getNick());
                        }

                case "CHECK":  CHECK oldX oldY newX newY
                    System.out.println("Wybrano: CHECK");
                    if (game.validateMove(players[currentPlayer], Integer.parseInt(msg[1]), Integer.parseInt(msg[2]),
                            Integer.parseInt(msg[3]), Integer.parseInt(msg[4]))) {
                        System.out.println("Ruch poprawny");
                        players[currentPlayer].sendMessage("ACCEPT "+Integer.parseInt(msg[1]) +" "+ Integer.parseInt(msg[2])+
                                " "+Integer.parseInt(msg[3]) +" "+ Integer.parseInt(msg[4]));
                    } else {
                        System.out.println("Ruch NIE poprawny");
                        players[currentPlayer].sendMessage("DECLINE");
                    }

                case "ERROR":
                    System.out.println("Wybrano: ERROR");
                    playing = false;
                    sendToEveryone("PLAYERQUIT " + players[currentPlayer].getNick());
            }*/
        }
        listener.close();
    }

    public Player [] getPlayers() {
        return players;
    }

    private void sendToEveryone(String message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    private void sendToEveryoneExceptCurrent(String message,Player currentPlayer)
    {
        for (Player player : players) {
            if(player.equals(currentPlayer))
            {
                System.out.println("beforesendingendmove");
                player.sendMessage("ENDMOVE");
                System.out.println("endmovesended");
            }
            else
                player.sendMessage(message);
        }
    }
}
