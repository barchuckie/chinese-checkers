package com.chinesecheckers.server.game;

/**
 * GameMode generates new game
 */
public interface GameMode {

    /**
     * Generate new game
     * @param data game data
     * @return new game instance
     */
    Game generateGame(GameData data);

    /**
     * The function returns game mode name in capitalized letters.
     * It is used to send its name between server and clients.
     * @return game mode name
     */
    String getName();
}
