package com.chinesecheckers.server;

public class GameServerState {

    enum ServerState {
        FREE,
        WAITING,
        INGAME
    }

    ServerState state;

    GameServerState() {
        state = ServerState.FREE;
    }

    public void setStateFree() {
        this.state = ServerState.FREE;
    }

    public void setStateWaiting() {
        this.state = ServerState.WAITING;
    }

    public void setStateInGame() {
        this.state = ServerState.INGAME;
    }

    public String stateToString() {
        return state.toString();
    }
}
