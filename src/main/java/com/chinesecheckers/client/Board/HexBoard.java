package com.chinesecheckers.client.Board;

import com.chinesecheckers.client.Circle;
import com.chinesecheckers.client.CircleField;

public abstract class HexBoard extends Board {

    public void setup(String type)
    {
        if(type.equals("STANDARD"))
        {
            fields = new CircleField[17][25];
        }
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
                CircleField ellipse2D = new CircleField(x,y,20);
                ellipse2D.setPlayer(0);
                CircleField ellipse2DD = new CircleField(x,y2,20);
                ellipse2DD.setPlayer(0);
                x=x+60;

                fields[a][b]=ellipse2D;
                fields[16-a][b]=ellipse2DD;
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
                fields[i][j]=null;
            }
        }
    }

}
