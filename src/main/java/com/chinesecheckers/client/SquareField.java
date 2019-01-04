package com.chinesecheckers.client;

import java.awt.*;

public class SquareField extends Square implements Field {

    public SquareField(double x, double y, double r, int player)
    {
        super(x,y,r,player);
    }

    public SquareField(double x, double y, double r)
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

    @Override
    public boolean doesContain(Point p)
    {
        return contains(p);
    }
}
