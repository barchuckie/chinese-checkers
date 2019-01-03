package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;

//TODO Wyróżnienie przy decline, kiedy pierwszy ruch(można zmienić piona) a kiedy już kolejny(nie można zmienić piona)

public class CircleGraphicPanel extends AbstractGraphicPanel {

    private CircleField fields[][];
    PrintWriter printWriter;
    private CircleField active;
    private int activeX,activeY;
    private int newX, newY;
    private int originalX,originalY;
    private boolean pawnChosen=false;
    private boolean myTurn=false;

    public CircleGraphicPanel(Board board, PrintWriter p)
    {
        board.addPlayers();
        fields =(CircleField[][]) board.getFields();
        this.printWriter=p;
        this.addMouseListener(new MyMouseAdapter());
    }
    //h=763 w=1000
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.red);

        for (Field[] circles : fields)
        {
            for (Field circle : circles)
            {
                if (circle!= null)
                {
                    g2d.draw((CircleField)circle);
                    g2d.setColor(PlayerColor.getColor(circle.getPlayer()));
                    if(circle==active)
                    {
                        g2d.setColor(PlayerColor.getColor(circle.getPlayer()).darker());
                    }
                    g2d.fill((CircleField)circle);
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
            if(myTurn)
            {
                //LPM  -- ruch do sprawdzenia
                if ((e.getButton() == 1))
                {
                    if (active != null)
                    {
                        if (getClickedField(e) != active && getClickedField(e) != null)
                        {
                            Circle circle = getClickedField(e); //czy ja tego potrzebuje?
                            sendMessage("CHECK", newX, newY);
                        }
                    }
                }
                //PPM
                else if ((e.getButton() == 3))
                {
                    //jesli pierwsze prawe klikniecie -- wybor piona
                    if (!pawnChosen)
                    {
                        if (getClickedField(e) != null)
                        {
                            CircleField circle = getClickedField(e);
                            setActive(circle);
                            pawnChosen = true;
                            originalX = newX;
                            originalY = newY;
                            repaint();
                        }

                    }
                    else  //drugie prawe klikniecie -- zakonczenie ruchu
                    {
                        if (getClickedField(e) != null)
                        {
                            CircleField circle = getClickedField(e);
                            if (circle.equals(active))
                            {
                                System.out.println("KONIEC RUCHU");
                                sendEndMessage("MOVE");
                                disactive();
                                repaint();
                            }
                        }
                    }
                }
            }
        }
    }
    private CircleField getClickedField(MouseEvent e)
    {
        for (int x = 0; x < 17; x++)
        {
            for (int y = 0; y < 25; y++)
            {
                if ((fields[x][y] != null) && (fields[x][y].contains(e.getPoint())))
                {
                    this.newX =x;
                    this.newY =y;
                    return fields[x][y];
                }
            }
        }
        return null;
    }

    public void setMyTurn()
    {
        myTurn=true;
    }

    public void setNotMyTurn()
    {
        myTurn=false;
        disactive();
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

    public void setActive(Field circle)
    {
        activeX = newX;
        activeY = newY;
        active = (CircleField) circle;
    }

    private void sendMessage(String action,int i,int j)
    {
        printWriter.println(action + " " + activeX + " " + activeY + " " + i + " " + j);
        System.out.println("sentMSG " + action + " " + activeX + " " + activeY + " " + i + " " + j);
    }
    private void sendEndMessage(String action)
    {
        printWriter.println(action + " " + originalX + " " + originalY + " " + activeX + " " + activeY);
        System.out.println("sentENDMSG " + action + " " + originalX + " " + originalY + " " + activeX + " " + activeY);
    }

}
