package com.chinesecheckers.client.Board;

import com.chinesecheckers.client.Field;

/**
 * Concrete class extending {@code HexBoard}. Represents six-arm star board with 4 players.
 * @see HexBoard
 * @see Board
 */
public class HexBoardFourPlayers extends HexBoard {

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPlayers() {
        for(int i = 4; i < 9; ++i) {
            for(int j = 0; j < 11-i; ++j) {
                Field field = fields[i][j];
                if(field != null) {
                    field.setPlayer(4);
                }
                field = fields[i][24-j];
                if(field != null) {
                    field.setPlayer(1);
                }
                field = fields[16-i][j];
                if(field != null) {
                    field.setPlayer(3);
                }
                field = fields[16-i][24-j];
                if(field != null) {
                    field.setPlayer(2);
                }
            }
        }
    }
}
