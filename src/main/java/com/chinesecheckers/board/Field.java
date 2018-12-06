package com.chinesecheckers.board;

import com.chinesecheckers.Player;

public class Field {
    private boolean isActive;
    private Player player;

    public Field(boolean isActive) {
        this.isActive = isActive;
        player = null;
    }

    public Field(boolean isActive, Player player) {
        this.isActive = isActive;
        this.player = player;
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
