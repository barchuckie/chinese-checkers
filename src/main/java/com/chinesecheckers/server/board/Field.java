package com.chinesecheckers.server.board;

import com.chinesecheckers.server.player.Player;

/**
 * Class representing a field on board in the server
 */
public class Field {

    private int x;
    private int y;

    /**
     * Player on the field
     */
    private Player player;

    /**
     * Fields surrounding the field (its neighbours)
     */
    private Field [] neighbours = new Field[6];

    /**
     * Creates new field and sets its player to {@code null}.
     */
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        player = null;
    }

    /**
     * Sets field's neighbours.
     * @param neighbours field's neighbours
     */
    public void setNeighbours(Field... neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * Sets field's player.
     * @param player player to be set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets field's neighbours.
     * @return array of field's neighbours
     */
    public Field[] getNeighbours() {
        return neighbours;
    }

    /**
     * Gets field's player
     * @return player taking the field
     */
    public Player getPlayer() {
        return player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
