package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;

import java.io.PrintWriter;

/**
 * Factory producing graphic panels
 * @see AbstractGraphicPanel
 */
public class PanelFactory {

    /**
     * Instantiates and returns graphic panels.
     * @param gameMode game mode
     * @param board game board
     * @param window window displaying the panel
     * @return graphic panel
     */
    public AbstractGraphicPanel getPanel (String gameMode, Board board, PlayWindow window) {
        if(gameMode.equals("STANDARD")) {
            return new CircleGraphicPanel(board, window);
        } else {
            return new SquareGraphicPanel(board, window);
        }
    }
}
