package com.chinesecheckers.server;

import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.BoardGenerator;

import java.util.ArrayList;

public class Game {
    private GameMode gameMode;
    private Player[] players;
    private Player currentPlayer;
    private int numOfPlayers;

    public Game(GameData data) {
        numOfPlayers = data.getNumOfPlayers();
        players = data.getPlayers();
        gameMode = data.getMode();
    }
}
