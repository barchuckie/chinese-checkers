package com.chinesecheckers.client.Board;

/* FACTORY METHOD PATTERN */

/**
 * Abstract class presenting schema for creating and accessing board.
 */
public abstract class BoardFactory {

    /**
     * Creates board
     * @param players number of players
     * @return created board
     */
    public abstract Board createBoard(int players);

    /**
     * Creates a new board and sets it up. Calls {@code createBoard(int players)} function.
     * @param players number of players
     * @param gameMode game mode
     * @return prepared board
     */
    public Board getBoard(int players,String gameMode) {
        Board board = createBoard(players);
        board.setup(gameMode);
        return board;
    }


}

