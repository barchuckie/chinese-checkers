package com.chinesecheckers.client.Board;

import com.chinesecheckers.client.Circle;

public class HexBoardTwoPlayers extends HexBoard {

    public HexBoardTwoPlayers()
    {
        super();
    }

    @Override
    public void addPlayers()
    {
        for(int i = 0; i < 4; ++i) {
            //gora
            for(Circle circle : circles[i]) {
                if(circle != null) {
                    circle.setPlayer(1);
                }
            }
            //dol
            for (Circle circle : circles[16-i]) {
                if(circle != null) {
                    circle.setPlayer(2);
                }
            }
        }
    }
}
