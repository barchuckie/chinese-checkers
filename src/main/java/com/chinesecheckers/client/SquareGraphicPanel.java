package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;

import java.io.PrintWriter;

public class SquareGraphicPanel extends AbstractGraphicPanel {
    private SquareField[][] fields;
    private MyMouseAdapter mouseAdapter;

    public SquareGraphicPanel(Board board, PlayWindow window)
    {
        super(board);
        fields = (SquareField[][]) board.getFields();
        mouseAdapter = new MyMouseAdapter(fields, window);
        this.addMouseListener(mouseAdapter);
    }

    public MyMouseAdapter getMyMouseAdapter()
    {
        return mouseAdapter;
    }
}
