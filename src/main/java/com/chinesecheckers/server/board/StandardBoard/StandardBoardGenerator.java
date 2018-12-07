package com.chinesecheckers.server.board.StandardBoard;

import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.BoardGenerator;

public class StandardBoardGenerator implements BoardGenerator {


    @Override
    public Board generateBoard(int numOfPlayers) {
        Board standardBoard = new StandardBoard(numOfPlayers);
        return standardBoard;
    }
}
