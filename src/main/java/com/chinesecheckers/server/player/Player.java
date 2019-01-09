package com.chinesecheckers.server.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class representing GameClient in a game
 */
public abstract class Player {
    /**
     * Player's nick
     */
    private String nick;

    /**
     * Determines whether player has finished a game
     */
    private boolean finished;

    /**
     * Gets player's nick
     * @return player's nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * Sets player's nick
     * @param nick player's nick
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Reads message from player
     * @return message
     */
    public abstract String [] read();

    /**
     * Sends message to the player
     * @param message message to send
     */
    public abstract void sendMessage(String message);

    /**
     * Gets {@code finished} value
     * @return true if player has finished, false if not
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Sets {@code finished} value
     * @param finished value to be set
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Checks if the player is a human or a bot
     * @return true if bot, false if not
     */
    public abstract boolean isBot();

}
