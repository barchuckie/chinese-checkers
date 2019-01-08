package com.chinesecheckers.client.Board;

import com.chinesecheckers.client.Field;

/**
 * Represents game board.
 */
public abstract class Board {
    /**
     * Fields on board.
     */
    Field[][] fields;

    /**
     * Gets all the fields.
     * @return fields
     */
    public Field[][] getFields()
    {
        return fields;
    }

    /**
     * Fill all the fields with null.
     */
    public abstract void fillWithNulls();

    /**
     * Sets up the board according to game mode.
     * @param gameMode game mode
     */
    public abstract void setup(String gameMode);

    /**
     * Adds players to the board.
     */
    public abstract void addPlayers();
}
