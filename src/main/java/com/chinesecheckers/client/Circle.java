package com.chinesecheckers.client;

import java.awt.geom.Ellipse2D;

public abstract class Circle extends Ellipse2D.Double {
    int player;

    public Circle(double x, double y,double r)
    {
        super(x-r,y-r,2*r,2*r);
    }

    public Circle(double x, double y,double r,int player)
    {
        super(x-r,y-r,2*r,2*r);
        this.player = player;
    }

}
