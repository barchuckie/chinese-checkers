package com.chinesecheckers;

import com.chinesecheckers.board.Field;

public class GameData {

    private GameMode mode;
    private int numOfPlayers;
    private Player [] players;

    public GameData(GameMode mode, int numOfPlayers, Player [] players) {
        this.mode = mode;
        this.numOfPlayers = numOfPlayers;
        this.players = players;
    }

    public void setMode(GameMode mode) {
        this.mode = mode;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public GameMode getMode() {
        return mode;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public Player[] getPlayers() {
        return players;
    }
}
