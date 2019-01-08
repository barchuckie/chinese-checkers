package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;

import java.io.PrintWriter;

/**
 * Concrete class extending {@code AbstractGraphicPanel}. Represents a panel with square fields.
 * @see AbstractGraphicPanel
 */
public class SquareGraphicPanel extends AbstractGraphicPanel {

    /**
     * Board square fields
     */
    private SquareField[][] fields;

    /**
     * Mouse adapter handling its events on the panel
     */
    private MyMouseAdapter mouseAdapter;

    /**
     * Initializes new instance of {@code SquareGraphicPanel}. Represents board fields with squares.
     * @param board game board shown on the panel
     * @param window window containing the panel
     */
    public SquareGraphicPanel(Board board, PlayWindow window) {
        super(board);
        fields = (SquareField[][]) board.getFields();
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
