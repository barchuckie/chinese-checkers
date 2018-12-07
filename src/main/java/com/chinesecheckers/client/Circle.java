package com.chinesecheckers.client;

import java.awt.geom.Ellipse2D;

public class Circle extends Ellipse2D.Double {

    public Circle(double x, double y,double r)
    {
        super(x-r,y-r,2*r,2*r);
    }

}
