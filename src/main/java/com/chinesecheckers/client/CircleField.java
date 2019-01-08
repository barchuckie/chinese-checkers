package com.chinesecheckers.client;

import java.awt.*;

/**
 * Represents game field on the graphic panel in circle.
 * @see Circle
 * @see Field
 */
public class CircleField extends Circle implements Field {

    /**
     * Instantiate class with given parameters.
     * @param x x coordinate
     * @param y y coordinate
     * @param r radius
     * @param player player on the field
     */
    public CircleField(double x, double y, double r, int player) {
        super(x,y,r,player);
    }

    /**
     * Instantiate class with given parameters.
     * @param x x coordinate
     * @param y y coordinate
     * @param r radius
     */
    public CircleField(double x, double y, double r) {
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
    public boolean doesContain(Point p) {
        return contains(p);
    }
}
