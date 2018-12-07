package com.chinesecheckers.board;

import java.util.HashMap;

public abstract class Board {

    protected Field[][] fields = new Field[17][25];

    /* Każda plansza będzie stworzona z pól nie? xd
     * Bo jak nie to trzeba to zdowngradeować */

    public Field[][] getFields() {
        return fields;
    }
}
