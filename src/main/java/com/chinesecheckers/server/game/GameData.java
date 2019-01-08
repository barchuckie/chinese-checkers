package com.chinesecheckers.server.game;

import com.chinesecheckers.server.Player;

/**
 * Class contains all necessary data to run a game.
 */
public class GameData {

    /**
     * Number of players playing the game.
     */
    private final int numOfPlayers;

    /**
     * The array of playing players.
     */
    private Player[] players;

    /**
     * Instantiate new GameData object.
     * @param numOfPlayers number of players playing the game
     * @param players the array of playing players
     */
    public GameData(int numOfPlayers, Player [] players) {
        this.numOfPlayers = numOfPlayers;
        this.players = players;
    }

    /**
     * Gets number of players
     * @return number of players
     */
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    /**
     * Gets all players playing game
     * @return players playing game
     */
    public Player[] getPlayers() {
        return players;
    }

}
