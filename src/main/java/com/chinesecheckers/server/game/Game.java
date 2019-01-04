package com.chinesecheckers.server.game;

import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.board.Board;

public abstract class Game {

    protected Player[] players;
    protected int currentPlayer;
    protected int numOfPlayers;
    protected Board board;

    protected Game(GameData data) {
        this.players = data.getPlayers();
        this.numOfPlayers = data.getNumOfPlayers();
    }

    public abstract boolean validateMove(Player player, int oldX, int oldY, int newX, int newY);

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void makeMove(Player player, int oldX, int oldY, int newX, int newY) {
        board.getFields()[oldX][oldY].setPlayer(null);
        board.getFields()[newX][newY].setPlayer(player);
    }

    public void nextTurn() {
        if(currentPlayer < numOfPlayers-1) {
            currentPlayer++;
        } else {
            currentPlayer = 0;
        }
    }

    public abstract boolean checkWinner(int currentPlayer);
}
