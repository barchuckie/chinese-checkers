package com.chinesecheckers.client;

import javafx.scene.shape.Ellipse;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;


/* TODO funkcja ktora przyjmuje tablice, przeszukuje cala tablice i na danych wspolrzednych
   TODO jesli mam jakiegos gracz to biore jego kolor i wstawiam
 */

public class GraphicPanel extends JPanel {

    private Ellipse2D ellipse2DS[][];

    public GraphicPanel(Ellipse2D ellipse2DS[][])
    {
        this.ellipse2DS=ellipse2DS;
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


}
