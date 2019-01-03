package com.chinesecheckers.server.board;

public abstract class Board {

    protected Field[][] fields;

    /* Każda plansza będzie stworzona z pól nie? xd
     * Bo jak nie to trzeba to zdowngradeować */

    public Field[][] getFields() {
        System.out.println("Pobieranie pól");
        return fields;
    }
}
