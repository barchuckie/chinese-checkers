package com.chinesecheckers.server.board;

public abstract class Board {

    protected final int ROWS = 17;
    protected final int COLUMNS = 25;
    protected Field[][] fields = new Field[ROWS][COLUMNS];

    /* Każda plansza będzie stworzona z pól nie? xd
     * Bo jak nie to trzeba to zdowngradeować */

    public Field[][] getFields() {
        return fields;
    }
}
