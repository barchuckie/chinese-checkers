package com.chinesecheckers.client;

import java.awt.*;

public class CircleField extends Circle implements Field {

    public CircleField(double x, double y, double r, int player)
    {
        super(x,y,r,player);
    }

    public CircleField(double x, double y, double r)
    {
        super(x,y,r);
    }

    public int getPlayer()
    {
        return player;
    }

    public void setPlayer(int player)
    {
        this.player = player;
    }

    public boolean doesContain(Point p)
    {
        return contains(p);
    }
}