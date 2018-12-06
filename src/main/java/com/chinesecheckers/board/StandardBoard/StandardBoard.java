package com.chinesecheckers.board.StandardBoard;

import com.chinesecheckers.GameData;
import com.chinesecheckers.board.Board;
import com.chinesecheckers.board.Field;

import java.util.HashMap;
import java.util.Set;

class StandardBoard extends Board {

    private int numOfPlayers;

    StandardBoard(int numOfPLayers) {
        /* code */
        this.numOfPlayers = numOfPLayers;
        setFields();
    }

    private void setFields() {

    }

}
