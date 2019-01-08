package com.chinesecheckers.server.board;

/**
 * Represents game board.
 */
public abstract class Board {

    /**
     * Fields on board.
     */
    protected Field[][] fields;

    /**
     * Gets board's fields.
     * @return fields
     */
    public Field[][] getFields() {
        return fields;
    }
}
