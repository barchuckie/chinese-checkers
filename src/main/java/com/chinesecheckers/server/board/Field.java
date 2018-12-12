package com.chinesecheckers.server.board;

import com.chinesecheckers.server.Player;

public class Field {

    private Player player;
    private Field [] neighbours = new Field[6];

    public Field() {
        player = null;
    }

    public Field(Player player) {
        this.player = player;
    }

    public void setNeighbours(Field[] neighbours) {
        this.neighbours = neighbours;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Field[] getNeighbours() {
        return neighbours;
    }

    public Player getPlayer() {
        return player;
    }
}
