package com.chinesecheckers.server.board.StandardBoard;

import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.BoardGenerator;

public class StandardBoardGenerator implements BoardGenerator {


    @Override
    public Board generateBoard(GameData data) {
        return new StandardBoard(data);
    }

}
