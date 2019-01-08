package com.chinesecheckers.client.Board;

import com.chinesecheckers.client.Field;

/**
 * Concrete class extending {@code HexBoard}. Represents six-arm star board with 4 players.
 * @see HexBoard
 * @see Board
 */
public class HexBoardTwoPlayers extends HexBoard {

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPlayers() {
        for(int i = 0; i < 4; ++i) {
            //gora
            for(Field field : fields[i]) {
                if(field != null) {
                    field.setPlayer(1);
                }
            }
            //dol
            for (Field field : fields[16-i]) {
                if(field != null) {
                    field.setPlayer(2);
                }
            }
        }
    }
}
