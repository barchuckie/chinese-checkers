package com.chinesecheckers.client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Mouse adapter handling mouse events on the graphic panel.
 */
public class MyMouseAdapter extends MouseAdapter {

    private Field[][] fields;
    private PlayWindow window;
    private int clickedX, clickedY, originalX, originalY,activeX,activeY;
    private boolean pawnChosen;
    private Field active,originalField;

    /**
     * Instantiate class. Sets fields that can be clicked and play window.
     * @param fields fields on the panel
     * @param window play window
     */
    public MyMouseAdapter(Field[][] fields, PlayWindow window) {
        this.fields = fields;
        this.window = window;
    }

    /**
     * Handles mouse press
     * @param e mouse press
     */
    public void mousePressed(MouseEvent e) {
        boolean leftClick=(e.getButton() == 1);
        boolean rightClick=(e.getButton() == 3);
        Field clickedField = getClickedField(e);
        if(window.isMyTurn() && clickedField!=null) {
            //LPM
            if(leftClick) {
                boolean doubleClick=e.getClickCount()==2;
                if(doubleClick) {
                    //żeby pominac najpierw musisz wybrać piona, pozniej robisz ruchy(lub nie) i podwojnym kliknieciem
                    //pomijasz ture(Twoje poprzednie ruchy jeśli je zrobiłes nie maja żadnego wplywu na rozgrywke)
                    if(pawnChosen) {
                        if (clickedField == active) {
                            window.sendPassMessage(originalX,originalY,activeX,activeY);
                            window.onPassMessage();
                            if(clickedField != originalField) {
                                fields[originalX][originalY].setPlayer(fields[activeX][activeY].getPlayer());
                                fields[activeX][activeY].setPlayer(0);
                            }
                            deactivate();
                            window.getPanel().repaint();
                        }
                    }
                } else if(active != null) { // gdy mamy już jakis wybrany
                    if (clickedField != active) {
                        window.sendCheckMessage(activeX, activeY, clickedX, clickedY);
                    }
                }
            } else if(rightClick) {
                //PPM
                //pierwszy klik prawy
                if(!pawnChosen) {
                    if(clickedField.getPlayer() == window.getPlayerID()) { //jesli moj pion
                        pawnChosen=true;
                        setActiveField(clickedX,clickedY);
                        setOriginalField(clickedX,clickedY);
                        window.getPanel().repaint();
                    }
                } else { //drugi klik prawy
                    if(active.equals(clickedField)) {
                        System.out.println("KONIEC RUCHU");
                        window.sendMoveMessage(originalX, originalY, clickedX, clickedY);
                        deactivate();
                        window.getPanel().repaint();
                    }
                }
            }
        }
    }

    /**
     * Gets clicked field by mouse
     * @param e mouse click
     * @return clicked field
     */
    private Field getClickedField(MouseEvent e) {
        for (int x = 0; x < 17; x++) {
            for (int y = 0; y < 25; y++) {
                if ((fields[x][y] != null) && (fields[x][y].doesContain(e.getPoint()))) {
                    clickedX=x;
                    clickedY=y;
                    return fields[x][y];
                }
            }
        }
        return null;
    }

    /**
     * Deactivates class fields
     */
    private void deactivate() {
        active=null;
        originalY=-1;
        originalX=-1;
        pawnChosen=false;
    }

    /**
     * Sets original field (the field before move).
     * @param x coordinate
     * @param y coordinate
     */
    private void setOriginalField(int x , int y) {
        originalField = fields[x][y];
        originalX=x;
        originalY=y;
    }

    /**
     * Sets active field, where player has moved.
     * @param activeX coordinate
     * @param activeY coordinate
     */
    public void setActiveField(int activeX,int activeY) {
        this.active = fields[activeX][activeY];
        this.activeX=activeX;
        this.activeY=activeY;
    }

    /**
     * Returns field where player is located temporary (active field).
     * @return active field
     */
    public Field getActive() {
        return active;
    }
}
