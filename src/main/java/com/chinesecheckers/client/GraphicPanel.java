package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;


public class GraphicPanel extends JPanel {

    private Circle circles[][];
    PrintWriter printWriter;
    private Circle active;
    private int activeX,activeY;
    private int newX, newY;
    private int originalX,originalY;
    private boolean pawnChosen=false;
    boolean myTurn=true;

    public GraphicPanel(Board board,PrintWriter p)
    {
        board.addPlayers();
        circles=board.getFields();
        this.printWriter=p;
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

    public void setMyTurn(boolean t)
    {
        myTurn=t;
    }

    public class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            //LPM
            if ((e.getButton() == 1))
            {
                if(active!=null)
                {
                    if(getClickedField(e)!=active && getClickedField(e)!=null)
                    {
                        Circle circle = getClickedField(e);
                        pawnChosen = true;
                        sendMessage("CHECK", newX, newY);
                        activeX = newX;
                        activeY = newY;
                        active = circle;
                    }
                }
            }
            //PPM
            else if ((e.getButton() == 3))
            {
                if(!pawnChosen)
                {
                    if(getClickedField(e)!=null)
                    {
                        Circle circle = getClickedField(e);
                        activeX = newX;
                        activeY = newY;
                        active = circle;
                        originalX= newX;
                        originalY= newY;
                    }

                }
                else
                {
                    if(getClickedField(e)!=null)
                    {
                        if(getClickedField(e)!=null)
                        {
                            Circle circle = getClickedField(e);
                            if (circle.equals(active))
                            {
                                System.out.println("KONIEC RUCHU");
                                sendEndMessage("MOVE");
                                disactive();
                            }
                        }
                    }
                }
            }
        }
    }
    private Circle getClickedField(MouseEvent e)
    {
        for (int x = 0; x < 17; x++)
        {
            for (int y = 0; y < 25; y++)
            {
                if ((circles[x][y] != null) && (circles[x][y].contains(e.getPoint())))
                {
                    this.newX =x;
                    this.newY =y;
                    return circles[x][y];
                }
            }
        }
        return null;
    }
    public void disactive()
    {
        active=null;
        activeX=-1;
        activeY=-1;
        originalY=-1;
        originalX=-1;
        pawnChosen=false;
    }

    private void sendMessage(String action,int i,int j)
    {
        if(myTurn)
        {
            System.out.println("Wysylam wiadomosc do serwera z ruchem ");
            printWriter.println(action + " " + activeX + " " + activeY + " " + i + " " + j);

        }
    }
    private void sendEndMessage(String action)
    {
        if(myTurn)
        {
            //System.out.println("End message sending");
            printWriter.println(action + " " + originalX + " " + originalY + " " + activeX + " " + activeY);
            //System.out.println("End message sended");
        }
    }
}
