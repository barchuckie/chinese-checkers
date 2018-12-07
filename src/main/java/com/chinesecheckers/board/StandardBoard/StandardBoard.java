package com.chinesecheckers.board.StandardBoard;
import com.chinesecheckers.board.Board;
import com.chinesecheckers.board.Field;

import java.util.Arrays;

class StandardBoard extends Board {

    private int numOfPlayers;

    StandardBoard(int numOfPLayers) {
        /* code */
        for (int i = 0; i < 17; ++i) {
            Arrays.fill(fields[i], new Field(false));
        }
        this.numOfPlayers = numOfPLayers;
        setFields();
        addPlayers(numOfPLayers);
    }

    private void setFields() {
        for(int i = 0; i < 13; i++) {
            for(int j = 12-i; j <= 12 + i; j = j + 2) {
                fields[i][j].setIsActive(true);
                fields[16-i][j].setIsActive(true);
            }
        }
    }

    public Field[][] getFields() {
        return fields;
    }

    private void addPlayers(int numOfPlayers) {
        switch (numOfPlayers) {
            case 2:
                break;

            case 3:
                break;

            case 6:
                break;

            default:
                //Throw exc
        }
    }

}
