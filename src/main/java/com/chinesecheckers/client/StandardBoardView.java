package com.chinesecheckers.client;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class StandardBoardView {
    private JFrame frame;
    private JPanel mainPanel;
    private Ellipse2D ellipse2DS[][] = new Circle[17][25];

    public StandardBoardView()
    {
        circlesToArray();
    }

    public void start()
    {
        frame = new JFrame("Gra");
        circlesToArray();
        mainPanel = new GraphicPanel(ellipse2DS);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(1014,800);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    private void circlesToArray()
    {
        for(int i=0;i<17;i++)
        {
            for(int j=0;j<25;j++)
            {
                ellipse2DS[i][j]=null;
            }
        }

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
                Ellipse2D ellipse2D = new Circle(x,y,20);
                Ellipse2D ellipse2DD = new Circle(x,y2,20);
                x=x+60;

                ellipse2DS[a][b]=ellipse2D;
                ellipse2DS[16-a][b]=ellipse2DD;
                b=b+2;
            }

            startingb--;
            b=startingb;
            a++;

            y=y+40;
            y2=y2-40;
        }
    }
}
