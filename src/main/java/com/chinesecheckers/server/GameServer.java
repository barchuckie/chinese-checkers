package com.chinesecheckers.server;

import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.game.GameMode;
import com.chinesecheckers.server.game.StandardGame.StandardGame;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * {@code GameServer} class creates and leads a game between connected players.
 * @see Player
 */
public class GameServer {

    private ServerSocket listener;
    private Player [] players;
    private int numOfPlayers;
    private int numOfBots;
    private GameMode gameMode;
    private Game game;
    private GameData data;

    /**
     * Creates an instance of GameServer with given parameters.
     * @param numOfPlayers number of players taking part in the game
     * @param numOfBots number of bots playing the game
     * @param gameMode  game mode
     */
    public GameServer(int numOfPlayers, int numOfBots, GameMode gameMode) {
        this.numOfPlayers = numOfPlayers;
        this.numOfBots = numOfBots;
        this.gameMode = gameMode;
    }

    /**
     * Implements server lifecycle.
     * @throws IOException if an I/O error occurs when opening the socket or waiting for a
     *                      connection
     */
    public void start() throws IOException {
        listener = new ServerSocket(8901);
        System.out.println("Chinese Checkers Server is Running");

        /* Connect with all players */
        connectPlayers();

        /* Create new game */
        createNewGame();

        /* Lead the game */
        leadGame();

        listener.close();
    }

    /**
     * Creates new game.
     */
    private void createNewGame() {
        data = new GameData(numOfPlayers, players);
        game = gameMode.generateGame(data);
        sendToEveryone("GAME " + gameMode.getName() + " " + numOfPlayers);
    }

    /**
     * Leads the game between players.
     */
    private void leadGame() {
        int currentPlayer;
        while(true) {
            currentPlayer = game.getCurrentPlayer();
            if(players[currentPlayer].isFinished()) {
                game.nextTurn();
                continue;
            }
            players[currentPlayer].sendMessage("YOURMOVE");
            String [] msg = players[currentPlayer].read();
            printMessage(msg);

            if ("MOVE".equals(msg[0])) {
                moveHandler(currentPlayer, msg);
            } else if ("CHECK".equals(msg[0])) {
                checkHandler(currentPlayer, msg);
            } else if ("PASS".equals(msg[0])) {
                passHandler(currentPlayer, msg);
            } else if ("ERROR".equals(msg[0])) {
                errorHandler(currentPlayer);
                break;
            }
        }
    }

    /**
     * Prints received message into output
     * @param msg received message
     */
    private void printMessage(String [] msg) {
        System.out.print("Otrzymano: ");
        for(String part : msg) System.out.print(part + " ");
        System.out.println();
    }

    /**
     * "MOVE" message handler
     * @param currentPlayer player making move
     * @param msg full message
     */
    private void moveHandler(int currentPlayer, String [] msg) {
        int originalX = Integer.parseInt(msg[1]);
        int originalY = Integer.parseInt(msg[2]);
        int newX = Integer.parseInt(msg[3]);
        int newY = Integer.parseInt(msg[4]);

        if (originalX != newX || originalY != newY) { // ignore if position has not changed
            // PLAYER_MOVED playerNick originalX originalY newX newY
            sendPlayerMovedMsg("PLAYERMOVED " + players[currentPlayer].getNick() + " " +
                    originalX + " " + originalY + " " + newX + " " + newY + " ", players[currentPlayer]);
            if(game.checkWinner(currentPlayer)) {
                sendToEveryone("VICTORY " + players[currentPlayer].getNick());
                players[currentPlayer].setFinished(true);
            }
            game.nextTurn();
        }
    }

    /**
     * "CHECK" message handler
     * @param currentPlayer player checking move
     * @param msg full message
     */
    private void checkHandler(int currentPlayer, String [] msg) {
        int oldX = Integer.parseInt(msg[1]);
        int oldY = Integer.parseInt(msg[2]);
        int newX = Integer.parseInt(msg[3]);
        int newY = Integer.parseInt(msg[4]);

        if (game.validateMove(players[currentPlayer], oldX, oldY, newX, newY)) {
            System.out.println("Ruch poprawny");
            game.makeMove(players[currentPlayer], oldX, oldY, newX, newY);
            players[currentPlayer].sendMessage("ACCEPT " + oldX + " " + oldY + " " + newX + " " + newY);
        } else {
            System.out.println("Ruch NIE poprawny");
            players[currentPlayer].sendMessage("DECLINE");
        }
    }

    /**
     * "PASS" message handler
     * @param currentPlayer player passing turn
     * @param msg full message
     */
    private void passHandler(int currentPlayer, String [] msg) {
        int originalX = Integer.parseInt(msg[1]);
        int originalY = Integer.parseInt(msg[2]);
        int newX = Integer.parseInt(msg[3]);
        int newY = Integer.parseInt(msg[4]);
        game.makeMove(players[currentPlayer],newX,newY,originalX,originalY);
        game.nextTurn();
    }

    /**
     * "ERROR" message handler
     * @param currentPlayer player disconnected
     */
    private void errorHandler(int currentPlayer) {
        sendToEveryone("PLAYERQUIT " + players[currentPlayer].getNick());
    }

    /**
     * Connects {@code numOfPlayers} players
     * @throws IOException if an I/O error occurs when  waiting for a connection
     */
    private void connectPlayers() throws IOException {
        players = new Player[numOfPlayers];
        for(int i = 0; i < numOfPlayers; ++i) {
            players[i] = new Player(listener.accept());
            System.out.println("Listener #" + i + " accepted");
            String [] nickMsg = players[i].read();
            if(!nickMsg[0].equals("NICK") || nickMsg.length < 2) {
                i--;
                continue;
            }
            players[i].setNick(nickMsg[1]);
            players[i].sendMessage("YOURID " + (i+1));
        }
    }

    /**
     * Sends message to every player
     * @param message message to send
     */
    private void sendToEveryone(String message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    /**
     * Sends message to every player except the current turn one
     * @param message message to send
     * @param currentPlayer current turn player
     */
    private void sendToEveryoneExceptCurrent(String message,Player currentPlayer) {
        for (Player player : players) {
            if(!player.equals(currentPlayer)) {
                player.sendMessage(message);
            }
        }
    }

    /**
     * Sends message after player moves
     * @param message message to send
     * @param currentPlayer current turn player
     */
    private void sendPlayerMovedMsg(String message,Player currentPlayer) {
        currentPlayer.sendMessage("ENDMOVE");
        sendToEveryoneExceptCurrent(message,currentPlayer);
    }

}
