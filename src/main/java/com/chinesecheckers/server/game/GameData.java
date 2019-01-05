package com.chinesecheckers.server.game;

import com.chinesecheckers.server.Player;

public class GameData {

    private final int numOfPlayers;
    private Player[] players;

    public GameData(int numOfPlayers, Player [] players) {
        this.numOfPlayers = numOfPlayers;
        this.players = players;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public Player[] getPlayers() {
        return players;
    }
}
