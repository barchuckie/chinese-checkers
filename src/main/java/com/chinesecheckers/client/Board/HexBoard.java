package com.chinesecheckers.client.Board;

import com.chinesecheckers.client.Board.Board;
import com.chinesecheckers.client.Circle;

public abstract class HexBoard extends Board {

    public HexBoard()
    {
        circles = new Circle[17][25];
        setup();
    }

    public void setup()
    {
        fillWithNulls();
        int startingX=500;
        int x;
        int y=30;
        int y2=670;
        int a=0;
        int b=12;
        int startingb=12;
        for(int i=0;i<13;i++)
        {
            x=startingX-30*i;
            //0-13
            for(int w=0;w<i+1;w++)
            {
                Circle ellipse2D = new Circle(x,y,20);
                Circle ellipse2DD = new Circle(x,y2,20);
                x=x+60;

                circles[a][b]=ellipse2D;
                circles[16-a][b]=ellipse2DD;
                b=b+2;
            }

            startingb--;
            b=startingb;
            a++;

            y=y+40;
            y2=y2-40;
        }
    }

    @Override
    public void fillWithNulls()
    {
        for(int i=0;i<17;i++)
        {
            for(int j=0;j<25;j++)
            {
                circles[i][j]=null;
            }
        }
    }

}
