package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/* TODO funkcja ktora przyjmuje tablice, przeszukuje cala tablice i na danych wspolrzednych
   TODO jesli mam jakiegos gracz to biore jego kolor i wstawiam
 */

public class GraphicPanel extends JPanel {

    private Circle circles[][];
    private Board board;

    public GraphicPanel(Board board)
    {
        this.board=board;
        board.addPlayers();
        circles=board.getFields();
        this.addMouseListener(new MyMouseAdapter());
    }
    //h=763 w=1000
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.red);

        for (Circle[] circles : circles)
        {
            for (Circle circle : circles)
            {
                if (circle!= null)
                {
                    g2d.draw(circle);
                    g2d.setColor(PlayerColor.getColor(circle.getPlayer()));
                    g2d.fill(circle);
                    g2d.setColor(Color.red);
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
                       if((circles[i][j] != null) && (circles[i][j].contains(e.getPoint())))
                       {
                           //co chcesz robić gdy klikne dane kółko
                           System.out.println("Kliknąłem kółko: "+i+"x"+j + " Player:"+ circles[i][j].getPlayer());
                       }
                    }
                }
            }
            repaint();
        }
    }




}
