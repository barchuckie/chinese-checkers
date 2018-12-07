package com.chinesecheckers.server.board.StandardBoard;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.Field;

import java.util.Arrays;

public class StandardBoard extends Board {

    private int numOfPlayers;

    StandardBoard(int numOfPLayers) {
        /* code */
        for(int i = 0; i < 17; i++) {
            for(int j = 0; j < 25; j++) {
                fields[i][j]=new Field(false);
            }
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
