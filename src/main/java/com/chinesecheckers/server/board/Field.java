package com.chinesecheckers.server.board;

import com.chinesecheckers.server.Player;

public class Field {
    private boolean isActive;
    private Player player;
    private Field [] neighbours = new Field[6];

    public Field(boolean isActive) {
        this.isActive = isActive;
        player = null;
    }

    public Field(boolean isActive, Player player) {
        this.isActive = isActive;
        this.player = player;
    }

    public void setNeighbours(Field[] neighbours) {
        this.neighbours = neighbours;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean getIsActive(){
        return isActive;
    }

    public Player getPlayer() {
        return player;
    }
}
