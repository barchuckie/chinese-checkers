package com.chinesecheckers.server.board;

import com.chinesecheckers.server.game.GameData;

public interface BoardGenerator {

    Board generateBoard(GameData data);

}
