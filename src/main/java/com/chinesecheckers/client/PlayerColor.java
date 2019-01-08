package com.chinesecheckers.client;

import java.awt.*;

/**
 * {@code PlayerColor} represents players colors of the pawns.
 */
public class PlayerColor {

    /**
     * Gets a proper player color based on his ID
     * @param player ID
     * @return player's color
     */
    public static Color getColor(int player) {
        switch(player) {
            case 1:
                return Color.GREEN;
            case 2:
                return Color.RED;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.YELLOW;
            case 5:
                return Color.ORANGE;
            case 6:
                return Color.MAGENTA;
            default:
                return Color.WHITE;
        }
    }

    /**
     * Gets a proper player based on his color.
     * @param color player's color
     * @return player ID
     */
    public static int getPlayer(Color color) {
        if(color==Color.GREEN)
            return 1;
        else if(color==Color.RED)
            return 2;
        else if(color==Color.BLUE)
            return 3;
        else if(color==Color.YELLOW)
            return 4;
        else if(color==Color.ORANGE)
            return 5;
        else if(color==Color.MAGENTA)
            return 6;
        else
            return 0;
    }

    /**
     * Gets player color in {@code String} based on his ID
     * @param player ID
     * @return color name in String
     */
    public static String getColorName(int player) {
        switch(player) {
            case 1:
                return "GREEN";
            case 2:
                return "RED";
            case 3:
                return "BLUE";
            case 4:
                return "YELLOW";
            case 5:
                return "ORANGE";
            case 6:
                return "MAGENTA";
            default:
                return "WHITE";
        }
    }


}
