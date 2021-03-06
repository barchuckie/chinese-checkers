package com.chinesecheckers.server.game;

import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.player.Player;
import com.chinesecheckers.server.board.Board;

/**
 * Class representing game in chinese checkers.
 * It takes care of the whole game logic.
 */
public abstract class Game {

    /**
     * Players playing the game.
     */
    protected Player[] players;

    /**
     * Player making move in current turn.
     */
    protected int currentPlayer;

    /**
     * Number of players playing the game.
     */
    protected int numOfPlayers;

    /**
     * Board that the game is played on
     */
    private Board board;

    /**
     * Creates new instance of game with given data
     * @param data game data
     */
    protected Game(GameData data) {
        this.players = data.getPlayers();
        this.numOfPlayers = data.getNumOfPlayers();
    }

    /**
     * Validates whether the move is correct or not
     * @param player player who has made the move
     * @param oldX old field x coordinate
     * @param oldY old field y coordinate
     * @param newX new field x coordinate
     * @param newY new field y coordinate
     * @return true if approved, otherwise false
     */
    public abstract boolean validateMove(Player player, int oldX, int oldY, int newX, int newY);

    /**
     * Gets player making move in current turn.
     * @return current player
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the game board, which is updated
     * @return game board
     */
    public Field[][] getBoardFields() {
        return board.getFields();
    }

    /**
     * Sets game board
     * @param board set board
     */
    protected void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Moves player from the old field to the new one.
     * @param player player who has made the move
     * @param oldX old field x coordinate
     * @param oldY old field y coordinate
     * @param newX new field x coordinate
     * @param newY new field y coordinate
     */
    public void makeMove(Player player, int oldX, int oldY, int newX, int newY) {
        getBoardFields()[oldX][oldY].setPlayer(null);
        getBoardFields()[newX][newY].setPlayer(player);
    }

    /**
     * Switches turn to next player.
     */
    public void nextTurn() {
        if(currentPlayer < numOfPlayers-1) {
            currentPlayer++;
        } else {
            currentPlayer = 0;
        }
    }

    /**
     * Checks whether the player has won the game
     * @param currentPlayer player to be checked
     * @return true if won, false if not
     */
    public abstract boolean checkWinner(int currentPlayer);

    /**
     * Returns destination arm for the given player.
     * @param player player which destination vertex is returned for
     * @return destination arm ID
     */
    public abstract int getDestination(int player);
}
