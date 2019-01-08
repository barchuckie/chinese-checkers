package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;

//TODO Wyróżnienie przy decline, kiedy pierwszy ruch(można zmienić piona) a kiedy już kolejny(nie można zmienić piona)
//miejsce z ktorego zaczynalem
//i bez poruszania

/**
 * Concrete class extending {@code AbstractGraphicPanel}. Represents a panel with circle fields.
 * @see AbstractGraphicPanel
 */
public class CircleGraphicPanel extends AbstractGraphicPanel {
    /**
     * Board circle fields
     */
    private CircleField[][] fields;

    /**
     * Mouse adapter handling its events on the panel
     */
    private MyMouseAdapter mouseAdapter;

    /**
     * Initializes new instance of {@code CircleGraphicPanel}. Represents board fields with circles.
     * @param board game board shown on the panel
     * @param window window containing the panel
     */
    public CircleGraphicPanel(Board board, PlayWindow window) {
        super(board);
        fields = (CircleField[][]) board.getFields();
        mouseAdapter = new MyMouseAdapter(fields, window);
        this.addMouseListener(mouseAdapter);
    }

    /**
     * Gets mouse adapter.
     * @return mouse adapter
     */
    public MyMouseAdapter getMyMouseAdapter() {
        return mouseAdapter;
    }
}

