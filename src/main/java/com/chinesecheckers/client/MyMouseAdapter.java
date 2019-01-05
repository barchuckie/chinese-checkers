package com.chinesecheckers.client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseAdapter extends MouseAdapter {

    private Field[][] fields;
    private PlayWindow window;
    private int clickedX, clickedY, originalX, originalY,activeX,activeY;
    private boolean pawnChosen;
    private Field active;

    public MyMouseAdapter(Field[][] fields, PlayWindow window)
    {
        this.fields = fields;
        this.window = window;
    }

    public void mousePressed(MouseEvent e) {
        if(window.isMyTurn() && (getClickedField(e) != null))
        {
            //LPM
            if(e.getButton() == 1)
            {
                if(active != null) // gdy mamy już jakis wybrany
                {
                    if (getClickedField(e) != active && getClickedField(e) != null)
                    {
                        window.sendCheckMessage(activeX, activeY, clickedX, clickedY);
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
                    assert field != null;
                    if(field.getPlayer() == window.getPlayerID()) //jesli moj pion
                    {
                        pawnChosen=true;
                        setActiveField(clickedX,clickedY);
                        setOriginalParam(clickedX,clickedY);
                        window.getPanel().repaint();
                    }
                }
                //drugi klik prawy
                else
                {
                    Field field = getClickedField(e);
                    if(active.equals(field))
                    {
                        System.out.println("KONIEC RUCHU");
                        window.sendMoveMessage(originalX, originalY, clickedX, clickedY);
                        disactive();
                        window.getPanel().repaint();
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
