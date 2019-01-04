package com.chinesecheckers.client;

import java.awt.*;

public interface Field {

    int getPlayer();

    void setPlayer(int player);

    boolean doesContain(Point p);
}
