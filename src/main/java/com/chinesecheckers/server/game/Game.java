package com.chinesecheckers.server.game;

import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.board.Board;

public abstract class Game {

    protected Player[] players;
    int currentPlayer;
    int numOfPlayers;
    protected Board board;

    Game(GameData data) {
        this.players = data.getPlayers();
        this.numOfPlayers = data.getNumOfPlayers();
    }

    public abstract boolean validateMove(Player player, int oldX, int oldY, int newX, int newY);

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}
