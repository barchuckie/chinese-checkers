package com.chinesecheckers.client;

import java.awt.*;

/**
 * Represents game field on the graphic panel in square.
 * @see Square
 * @see Field
 */
public class SquareField extends Square implements Field {

    /**
     * Instantiate class with given parameters.
     * @param x x coordinate
     * @param y y coordinate
     * @param r length between centre and edge
     * @param player player on the field
     */
    public SquareField(double x, double y, double r, int player) {
        super(x,y,r,player);
    }

    /**
     * Instantiate class with given parameters.
     * @param x x coordinate
     * @param y y coordinate
     * @param r length between centre and edge
     */
    public SquareField(double x, double y, double r) {
        super(x,y,r);
    }

    /**
     * {@inheritDoc}
     */
    public int getPlayer() {
        return player;
    }

    /**
     * {@inheritDoc}
     */
    public void setPlayer(int player) {
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doesContain(Point p) {
        return contains(p);
    }
}
