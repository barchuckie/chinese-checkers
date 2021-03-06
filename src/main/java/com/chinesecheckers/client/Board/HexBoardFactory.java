package com.chinesecheckers.client.Board;

/**
 * Concrete class extending BoardFactory. Creates six-arm star board.
 */
public class HexBoardFactory extends BoardFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Board createBoard(int players) {
        if (players == 2)
            return new HexBoardTwoPlayers();
        else if (players == 4)
            return new HexBoardFourPlayers();
        else if (players == 3)
            return new HexBoardThreePlayers();
        else if (players == 6)
            return new HexBoardSixPlayers();
        else return null;
    }
}