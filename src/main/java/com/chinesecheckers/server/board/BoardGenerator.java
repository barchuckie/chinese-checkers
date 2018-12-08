package com.chinesecheckers.server.board;

import com.chinesecheckers.server.GameData;

public interface BoardGenerator {

    Board generateBoard(GameData data);

}
