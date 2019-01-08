package com.chinesecheckers.server.game.StandardGame;

import com.chinesecheckers.server.game.Game;
import com.chinesecheckers.server.game.GameData;
import com.chinesecheckers.server.game.GameMode;

/**
 * Concrete game mode implementing {@code GameMode}
 * @see GameMode
 */
public class StandardGameMode implements GameMode {

    /**
     * Generate new standard game
     * @param data game data
     * @return new standard game instance
     */
    @Override
    public Game generateGame(GameData data) {
        return new StandardGame(data);
    }

    /**
     * {@inheritDoc}
     * @return "STANDARD"
     */
    @Override
    public String getName() {
        return "STANDARD";
    }

}
