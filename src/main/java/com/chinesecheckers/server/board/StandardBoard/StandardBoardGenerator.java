package com.chinesecheckers.server.board.StandardBoard;

import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.BoardGenerator;

/**
 * Class generating {@code StandardBoard}. Part of Factory Method Pattern.
 * @see BoardGenerator
 * @see StandardBoard
 */
public class StandardBoardGenerator implements BoardGenerator {

    /**
     * Generates new standard game board.
     * @param data necessary data to setup
     * @return standard board
     */
    @Override
    public Board generateBoard(GameData data) {
        return new StandardBoard(data);
    }

}
