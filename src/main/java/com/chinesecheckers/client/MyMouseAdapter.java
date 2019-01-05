package com.chinesecheckers.client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseAdapter extends MouseAdapter {

    private Field[][] fields;
    private PlayWindow window;
    private int clickedX, clickedY, originalX, originalY,activeX,activeY;
    private boolean pawnChosen;
    private Field active,originalField;

    public MyMouseAdapter(Field[][] fields, PlayWindow window)
    {
        this.fields = fields;
        this.window = window;
    }

    public void mousePressed(MouseEvent e) {
        boolean leftClick=(e.getButton() == 1);
        boolean rightClick=(e.getButton() == 3);
        Field clickedField = getClickedField(e);
        if(window.isMyTurn() && clickedField!=null)
        {
            //LPM
            if(leftClick)
            {
                boolean doubleClick=e.getClickCount()==2;
                if(doubleClick)
                {
                    //żeby pominac najpierw musisz wybrać piona, pozniej robisz ruchy(lub nie) i podwojnym kliknieciem
                    //pomijasz ture(Twoje poprzednie ruchy jeśli je zrobiłes nie maja żadnego wplywu na rozgrywke)
                    if(pawnChosen)
                    {
                        if (clickedField == active)
                        {
                            window.sendPassMessage(originalX,originalY,activeX,activeY);
                            window.onPassMessage();
                            if(clickedField != originalField)
                            {
                                fields[originalX][originalY].setPlayer(fields[activeX][activeY].getPlayer());
                                fields[activeX][activeY].setPlayer(0);
                            }
                            disactive();
                            window.getPanel().repaint();
                        }
                    }
                }
                else if(active != null) // gdy mamy już jakis wybrany
                {
                    if (clickedField != active)
                    {
                        window.sendCheckMessage(activeX, activeY, clickedX, clickedY);
                    }
                }
            }
            //PPM
            else if(rightClick)
            {
                //pierwszy klik prawy
                if(!pawnChosen)
                {
                    if(clickedField.getPlayer() == window.getPlayerID()) //jesli moj pion
                    {
                        pawnChosen=true;
                        setActiveField(clickedX,clickedY);
                        setOriginalField(clickedX,clickedY);
                        window.getPanel().repaint();
                    }
                }
                //drugi klik prawy
                else
                {
                    if(active.equals(clickedField))
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

    private void setOriginalField(int x , int y)
    {
        originalField = fields[x][y];
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
