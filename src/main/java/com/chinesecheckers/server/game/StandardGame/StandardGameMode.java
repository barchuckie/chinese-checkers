package com.chinesecheckers.server.game.StandardGame;

import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.game.GameMode;

public class StandardGameMode implements GameMode {

    @Override
    public Game generateGame(GameData data) {
        return new StandardGame(data);
    }

}
