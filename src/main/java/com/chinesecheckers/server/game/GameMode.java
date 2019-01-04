package com.chinesecheckers.server.game;

public interface GameMode {

    Game generateGame(GameData data);

    String getName();
}
