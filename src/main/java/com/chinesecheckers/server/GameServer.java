package com.chinesecheckers.server;

import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.game.GameMode;
import com.chinesecheckers.server.game.StandardGame.StandardGame;

import java.net.ServerSocket;

public class GameServer {

    private ServerSocket listener;
    private Player [] players;
    private int numOfPlayers;
    private int numOfBots;
    private GameMode gameMode;
    private Game game;
    private GameData data;

    public GameServer(int numOfPlayers, int numOfBots, GameMode gameMode) {
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
        game = gameMode.generateGame(data);
        sendToEveryone("GAME " + gameMode.toString() + " " + numOfPlayers);

        /* Lead the game */
        int currentPlayer;
        boolean blockFurtherMove = false;
        while(true) {
            currentPlayer = game.getCurrentPlayer();
            players[currentPlayer].sendMessage("YOURMOVE");
            String [] msg = players[currentPlayer].read();
            System.out.print("Otrzymano: ");
            for(String part : msg) System.out.print(part + " ");
            System.out.println();

            // MOVE originalX originalY newX newY
            if ("MOVE".equals(msg[0])) {
                int originalX = Integer.parseInt(msg[1]);
                int originalY = Integer.parseInt(msg[2]);
                int newX = Integer.parseInt(msg[3]);
                int newY = Integer.parseInt(msg[4]);
                if(originalX != newX || originalY != newY) { // ignore if position has not changed
                    // PLAYER_MOVED playerNick originalX originalY newX newY
                    sendPlayerMovedMsg("PLAYERMOVED " + players[currentPlayer].getNick() + " " +
                            originalX + " " + originalY + " " + newX + " " + newY + " ", players[currentPlayer]);
                    if(game.checkWinner(currentPlayer)) {
                        sendToEveryone("VICTORY " + players[currentPlayer].getNick());
                    }
                    blockFurtherMove = false;
                    game.nextTurn();
                }
            } else if ("CHECK".equals(msg[0])) {
                int oldX = Integer.parseInt(msg[1]);
                int oldY = Integer.parseInt(msg[2]);
                int newX = Integer.parseInt(msg[3]);
                int newY = Integer.parseInt(msg[4]);
                if (game.validateMove(players[currentPlayer], oldX, oldY, newX, newY)) {
                    if(!blockFurtherMove) {
                        if(isSimpleMove(oldX, oldY, newX, newY)) {
                            blockFurtherMove = true;
                        }
                        System.out.println("Ruch poprawny");
                        game.makeMove(players[currentPlayer], oldX, oldY, newX, newY);
                        players[currentPlayer].sendMessage("ACCEPT " + oldX + " " + oldY + " " + newX + " " + newY);
                    } else {
                        System.out.println("Brak możliwości dalszego ruchu");
                        players[currentPlayer].sendMessage("DECLINE");
                    }
                } else {
                    System.out.println("Ruch NIE poprawny");
                    players[currentPlayer].sendMessage("DECLINE");
                }
            } else if ("ERROR".equals(msg[0])) {
                sendToEveryone("PLAYERQUIT " + players[currentPlayer].getNick());
                break;
            }
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

    private void sendToEveryoneExceptCurrent(String message,Player currentPlayer) {
        for (Player player : players) {
            if(!player.equals(currentPlayer)) {
                player.sendMessage(message);
            }
        }
    }

    private void sendPlayerMovedMsg(String message,Player currentPlayer) {
        currentPlayer.sendMessage("ENDMOVE");
        sendToEveryoneExceptCurrent(message,currentPlayer);
    }

    private boolean isSimpleMove(int oldX, int oldY, int newX, int newY) {
        return game.getBoard().getFields()[oldX][oldY].isNeighbourWith(game.getBoard().getFields()[newX][newY]);
    }
}
