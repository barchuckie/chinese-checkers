package com.chinesecheckers.client.Board;

import com.chinesecheckers.client.Circle;

public abstract class Board {
    Circle[][] circles;

    public Circle[][] getFields()
    {
        return circles;
    }

    public abstract void fillWithNulls();

    public abstract void setup();

    public abstract void addPlayers();
}
