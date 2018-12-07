package com.chinesecheckers.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

/* TODO funkcja ktora przyjmuje tablice, przeszukuje cala tablice i na danych wspolrzednych
   TODO jesli mam jakiegos gracz to biore jego kolor i wstawiam
 */

public class GraphicPanel extends JPanel {

    private Ellipse2D ellipse2DS[][];

    public GraphicPanel(Ellipse2D ellipse2DS[][])
    {
        this.ellipse2DS=ellipse2DS;
        this.addMouseListener(new MyMouseAdapter());
    }
    //h=763 w=1000
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);

        for (Ellipse2D[] ellipse2D : ellipse2DS)
        {
            for (Ellipse2D anEllipse2D : ellipse2D)
            {
                if (anEllipse2D != null)
                {
                    g2d.draw(anEllipse2D);
                }
            }
        }
    }

    public class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            if ((e.getButton() == 1)) {
                for(int i = 0; i < 17; i++) {
                    for(int j = 0; j < 25; j++) {
                       if((ellipse2DS[i][j] != null) && (ellipse2DS[i][j].contains(e.getPoint())))
                       {
                           //co chcesz robić gdy klikne dane kółko
                           System.out.println("Kliknąłem kółko: "+i+"x"+j);
                       }
                    }
                }
            }
            repaint();
        }
    }




}
