package com.chinesecheckers.client;

import java.awt.geom.Ellipse2D;

public abstract class Circle extends Ellipse2D.Double {

    /**
     * ID of player on the field
     */
    int player;

    /**
     * Instantiate class with given parameters.
     * @param x x coordinate
     * @param y y coordinate
     * @param r length between centre and edge
     */
    public Circle(double x, double y,double r) {
        super(x-r,y-r,2*r,2*r);
    }

    /**
     * Instantiate class with given parameters.
     * @param x x coordinate
     * @param y y coordinate
     * @param r radius
     * @param player player on the field
     */
    public Circle(double x, double y,double r,int player) {
        super(x-r,y-r,2*r,2*r);
        this.player = player;
    }

}
