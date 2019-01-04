package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;

public class SquareGraphicPanel extends AbstractGraphicPanel {
    private SquareField[][] fields;
    MyMouseAdapter mouseAdapter;

    public SquareGraphicPanel(Board board, PrintWriter p)
    {
        super(p,board);
        fields = (SquareField[][]) board.getFields();
        mouseAdapter = new MyMouseAdapter(fields,this);
        this.addMouseListener(mouseAdapter);
    }

    public MyMouseAdapter getMyMouseAdapter()
    {
        return mouseAdapter;
    }
}
