package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;

//TODO Wyróżnienie przy decline, kiedy pierwszy ruch(można zmienić piona) a kiedy już kolejny(nie można zmienić piona)
//miejsce z ktorego zaczynalem
//i bez poruszania

public class CircleGraphicPanel extends AbstractGraphicPanel {
    private CircleField[][] fields;
    MyMouseAdapter mouseAdapter;

    public CircleGraphicPanel(Board board, PrintWriter p)
    {
        super(p,board);
        fields = (CircleField[][]) board.getFields();
        mouseAdapter = new MyMouseAdapter(fields,this);
        this.addMouseListener(mouseAdapter);
    }

    public MyMouseAdapter getMyMouseAdapter()
    {
        return mouseAdapter;
    }
}

