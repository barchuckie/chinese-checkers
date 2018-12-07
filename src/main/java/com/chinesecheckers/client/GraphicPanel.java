package com.chinesecheckers.client;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;

public class GraphicPanel extends JPanel {

        Ellipse2D ellipse2DS[][] = new Circle[17][25];
        Set<Ellipse2D> set = new HashSet<>();
        int between;

        //h=763 w=1000
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.red);
            circlesToSet();

        /*
        for(Ellipse2D k : set )
        {
            g2d.draw(k);
        }
        */
            for(int i=0;i<17;i++)
            {
                for(int j=0;j<25;j++)
                {
                    if(ellipse2DS[i][j]!=null)
                    {
                        g2d.draw(ellipse2DS[i][j]);
                    }
                }
            }
        }

        //zapisuje w secie i tablicy 17x25
        public void circlesToSet()
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
                    set.add(ellipse2D);
                    set.add(ellipse2DD);


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
