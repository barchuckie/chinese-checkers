package com.chinesecheckers.client;

import java.awt.geom.Rectangle2D;

public abstract class Square extends Rectangle2D.Double {
    int player;

    public Square(double x, double y,double r)
    {
        super(x-r,y-r,2*r,2*r);
    }

    public Square(double x, double y,double r,int player)
    {
        super(x-r,y-r,2*r,2*r);
        this.player = player;
    }
}
