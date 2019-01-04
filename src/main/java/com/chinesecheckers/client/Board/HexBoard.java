package com.chinesecheckers.client.Board;

import com.chinesecheckers.client.CircleField;
import com.chinesecheckers.client.Field;
import com.chinesecheckers.client.SquareField;

public abstract class HexBoard extends Board {

    Field field;
    Field field2;
    public void setup(String type)
    {
        if(type.equals("STANDARD"))
        {
            fields = new CircleField[17][25];
        }
        else
        {
            fields = new SquareField[17][25];
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
                if(type.equals("STANDARD"))
                {
                    field = new CircleField(x,y,20);
                    field2 = new CircleField(x,y2,20);
                }
                else
                {
                    field = new SquareField(x,y,20);
                    field2 = new SquareField(x,y2,20);
                }
                field.setPlayer(0);
                field2.setPlayer(0);
                x=x+60;

                fields[a][b]=field;
                fields[16-a][b]=field2;
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
