package com.chinesecheckers.client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseAdapter extends MouseAdapter {

    private Field[][] fields;
    private AbstractGraphicPanel panel;
    private int clickedX, clickedY, originalX, originalY,activeX,activeY;
    private boolean pawnChosen;
    private Field active;

    public MyMouseAdapter(Field fields[][], AbstractGraphicPanel panel)
    {
        this.fields = fields;
        this.panel = panel;
    }

    public void mousePressed(MouseEvent e)
    {
        if(panel.myTurn && getClickedField(e)!=null)
        {
            //LPM
            if(e.getButton() == 1)
            {
                if(active!=null) // gdy mamy już jakis wybrany
                {
                    if (getClickedField(e) != active && getClickedField(e) != null)
                    {
                        Field field = getClickedField(e);
                        panel.sendMessage("CHECK",activeX,activeY,clickedX,clickedY);
                    }
                }
            }
            //PPM
            else if(e.getButton() == 3)
            {
                //pierwszy klik prawy
                if(!pawnChosen)
                {
                    Field field = getClickedField(e);
                    if(field.getPlayer()==panel.getPlayerID()) //jesli moj pion
                    {
                        pawnChosen=true;
                        setActiveField(clickedX,clickedY);
                        setOriginalParam(clickedX,clickedY);
                        panel.repaint();
                    }
                }
                //drugi klik prawy
                else
                {
                    Field field = getClickedField(e);
                    if(field.equals(active))
                    {
                        System.out.println("KONIEC RUCHU");
                        panel.sendMessage("MOVE",originalX,originalY,clickedX,clickedY);
                        disactive();
                        panel.repaint();
                    }
                }
            }
        }
    }

    private Field getClickedField(MouseEvent e)
    {
        for (int x = 0; x < 17; x++)
        {
            for (int y = 0; y < 25; y++)
            {
                if ((fields[x][y] != null) && (fields[x][y].doesContain(e.getPoint())))
                {
                    clickedX=x;
                    clickedY=y;
                    return fields[x][y];
                }
            }
        }
        return null;
    }

    private void disactive()
    {
        active=null;
        originalY=-1;
        originalX=-1;
        pawnChosen=false;
    }

    private void setOriginalParam(int x ,int y)
    {
        originalX=x;
        originalY=y;
    }

    public void setActiveField(int activeX,int activeY)
    {
        this.active = fields[activeX][activeY];
        this.activeX=activeX;
        this.activeY=activeY;
    }

    public Field getActive()
    {
        return active;
    }
}
