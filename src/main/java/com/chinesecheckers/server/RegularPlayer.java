package com.chinesecheckers.server;

public class RegularPlayer implements Player {
    private String nick;

    public RegularPlayer(String nick) {
        this.nick = nick;
    }

    @Override
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
