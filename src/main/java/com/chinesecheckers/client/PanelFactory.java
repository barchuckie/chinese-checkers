package com.chinesecheckers.client;

import com.chinesecheckers.client.Board.Board;

import java.io.PrintWriter;

public class PanelFactory {

    public AbstractGraphicPanel getPanel (String gameMode, Board board, PlayWindow window) {
        if(gameMode.equals("STANDARD")) {
            return new CircleGraphicPanel(board, window);
        } else {
            return new SquareGraphicPanel(board, window);
        }
    }
}
