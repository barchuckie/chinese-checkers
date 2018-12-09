package com.chinesecheckers.client;

import java.awt.geom.Ellipse2D;

public class Circle extends Ellipse2D.Double {
    private int player;

    public Circle(double x, double y,double r)
    {
        super(x-r,y-r,2*r,2*r);
    }

    public Circle(double x, double y,double r,int player)
    {
        super(x-r,y-r,2*r,2*r);
        this.player = player;
    }

    public int getPlayer()
    {
        return player;
    }

    public void setPlayer(int player)
    {
        this.player = player;
    }

}
