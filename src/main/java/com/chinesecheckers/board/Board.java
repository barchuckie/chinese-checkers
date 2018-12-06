package com.chinesecheckers.board;

import java.util.HashMap;

public abstract class Board {

    protected HashMap<Integer, HashMap<Integer, Field>> fields;

    /* Musi być HashMap, bo środek planszy będzie środkiem ukł. współ., więc będą też ujemne klucze.
     * Każda plansza będzie stworzona z pól nie? xd
     * Bo jak nie to trzeba to zdowngradeować */
}
