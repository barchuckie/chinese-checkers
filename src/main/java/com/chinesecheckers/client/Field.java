package com.chinesecheckers.client;

import java.awt.*;

/**
 * Class representing a field on board in the client
 */
public interface Field {

    /**
     * Gets player ID on the field
     * @return player ID
     */
    int getPlayer();

    /**
     * Set player ID on the field
     * @param player ID
     */
    void setPlayer(int player);

    /**
     * Checks if the field contains the point
     * @param p point to be check
     * @return true if contains, false if not
     */
    boolean doesContain(Point p);
}
