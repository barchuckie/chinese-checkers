package com.chinesecheckers.server.board;

import com.chinesecheckers.server.game.GameData;

/**
 * Generates game board. It is part of Factory Method pattern.
 */
public interface BoardGenerator {

    /**
     * Generates new game board.
     * @param data necessary data to setup
     * @return created board
     */
    Board generateBoard(GameData data);

}
