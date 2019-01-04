package com.chinesecheckers.client.Board;

import com.chinesecheckers.client.Field;

public abstract class Board {
    Field[][] fields;

    public Field[][] getFields()
    {
        return fields;
    }

    public abstract void fillWithNulls();

    public abstract void setup(String gameMode);

    public abstract void addPlayers();
}
