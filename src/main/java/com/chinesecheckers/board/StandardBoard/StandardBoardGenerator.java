package com.chinesecheckers.board.StandardBoard;

import com.chinesecheckers.GameData;
import com.chinesecheckers.board.Board;
import com.chinesecheckers.board.BoardGenerator;

public class StandardBoardGenerator implements BoardGenerator {


    @Override
    public Board generateBoard(int numOfPlayers) {
        Board standardBoard = new StandardBoard(numOfPlayers);
        return standardBoard;
    }
}
