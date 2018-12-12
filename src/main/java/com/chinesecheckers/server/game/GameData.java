package com.chinesecheckers.server.game;

import com.chinesecheckers.server.Player;

public class GameData {

    private int numOfPlayers;
    private Player[] players;

    public GameData(int numOfPlayers, Player [] players) {
        this.numOfPlayers = numOfPlayers;
        this.players = players;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public Player[] getPlayers() {
        return players;
    }
}
